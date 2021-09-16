package com.clonecodingbm.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val BASE_URL =
        "https://r5670326j8.execute-api.ap-northeast-2.amazonaws.com/delivery_server/"

    private val okHttpClient: OkHttpClient by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        OkHttpClient.Builder()
            .readTimeout((60 * 2).toLong(), TimeUnit.SECONDS)
            .connectTimeout((60 * 2).toLong(), TimeUnit.SECONDS)
            .writeTimeout((60 * 2).toLong(), TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    private val buildRetrofitClient: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    val getApiService: ApiService by lazy {
        buildRetrofitClient.create(ApiService::class.java)
    }
}