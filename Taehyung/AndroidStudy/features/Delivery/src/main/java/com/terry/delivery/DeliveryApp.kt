package com.terry.delivery

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/*
 * Created by Taehyung Kim on 2021-09-23
 */
@HiltAndroidApp
class DeliveryApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
