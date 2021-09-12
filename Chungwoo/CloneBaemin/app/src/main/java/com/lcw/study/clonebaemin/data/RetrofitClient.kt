package com.lcw.study.clonebaemin.data

import com.lcw.study.clonebaemin.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun getService(): ApiService {

        return retrofitBuilder.create(ApiService::class.java)
    }

    private val retrofitBuilder = Retrofit
        .Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}