package com.terry.delivery

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.terry.delivery.base.BaseFragment
import com.terry.delivery.databinding.FragmentSplashBinding
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import java.util.concurrent.TimeUnit

/*
 * Created by Taehyung Kim on 2021-09-07
 */
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startTimer()
    }

    private fun startTimer() {
        Completable
            .timer(1200L, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
            }, {
                it.printStackTrace()
            })
            .addTo(disposable)
    }

}