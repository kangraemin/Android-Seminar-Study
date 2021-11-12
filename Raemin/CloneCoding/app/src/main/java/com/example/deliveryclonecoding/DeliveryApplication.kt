package com.example.deliveryclonecoding

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins

@HiltAndroidApp
class DeliveryApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler {
            if (it is UndeliverableException) {
                return@setErrorHandler
            }
        }
    }
}