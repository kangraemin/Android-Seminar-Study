package com.terry.delivery.api

import com.terry.delivery.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/*
 * Created by Taehyung Kim on 2021-09-08
 */

const val BASE_URL = "https://r5670326j8.execute-api.ap-northeast-2.amazonaws.com/delivery_server/"

val retrofit = provideRetrofit()

fun provideLoginService(): LoginService = retrofit.create(LoginService::class.java)

private fun provideOkHttpClient() = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply {
        level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    })
    .build()

private fun provideRetrofit(): Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(provideOkHttpClient())
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()
