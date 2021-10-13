package com.terry.delivery

import androidx.annotation.NonNull
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.util.concurrent.TimeUnit

/*
 * Created by Taehyung Kim on 2021-10-06
 */
class ImmediateSchedulerRuleTest : TestRule {
    private val immediateScheduler = object : Scheduler() {
        @NonNull
        override fun scheduleDirect(
            @NonNull run: Runnable,
            delay: Long,
            @NonNull unit: TimeUnit
        ): Disposable {
            // Hack to prevent stack overflows in unit tests when scheduling with a delay;
            return super.scheduleDirect(run, 0, unit)
        }

        override fun createWorker(): Scheduler.Worker {
            return ExecutorScheduler.ExecutorWorker(
                { it.run() }, false
            )
        }
    }

    @NonNull
    override fun apply(@NonNull base: Statement, @NonNull description: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                RxJavaPlugins.setInitIoSchedulerHandler { immediateScheduler }
                RxJavaPlugins.setInitComputationSchedulerHandler { immediateScheduler }
                RxJavaPlugins.setInitNewThreadSchedulerHandler { immediateScheduler }
                RxJavaPlugins.setInitSingleSchedulerHandler { immediateScheduler }
                RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediateScheduler }

                try {
                    base.evaluate()
                } finally {
                    RxJavaPlugins.reset()
                    RxAndroidPlugins.reset()
                }
            }
        }
    }
}