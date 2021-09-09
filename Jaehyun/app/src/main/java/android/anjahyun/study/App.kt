package android.anjahyun.study

import android.anjahyun.study.network.ApiClient
import android.app.Application

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        ApiClient.initServices()
    }

}