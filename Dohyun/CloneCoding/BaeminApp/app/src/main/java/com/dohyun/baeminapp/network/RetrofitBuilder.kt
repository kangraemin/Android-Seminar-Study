package com.dohyun.baeminapp.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    const val baseUrl = "https://r5670326j8.execute-api.ap-northeast-2.amazonaws.com/delivery_server/"

    var apiClient: ApiClient

    val gson = GsonBuilder().setLenient().create()

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        apiClient = retrofit.create(ApiClient::class.java)
    }
}