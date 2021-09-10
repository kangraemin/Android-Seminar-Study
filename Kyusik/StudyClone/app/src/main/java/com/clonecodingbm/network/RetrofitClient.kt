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
    private var retrofit: Retrofit? = null

    private fun getRetrofitClient(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .readTimeout((60 * 2).toLong(), TimeUnit.SECONDS)
            .connectTimeout((60 * 2).toLong(), TimeUnit.SECONDS)
            .writeTimeout((60 * 2).toLong(), TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
        }
        return retrofit!!
    }

    val getApiService: ApiService
        get() = getRetrofitClient().create(ApiService::class.java)
}