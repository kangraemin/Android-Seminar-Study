package com.dohyun.baeminapp.data.remote

import com.dohyun.baeminapp.data.entity.*
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface ApiService {

    @POST("api/token/")
    fun login(@Body user: UserInfo) : Single<Token>

    @POST("api/token/refresh/")
    fun getAccessToken(@Body refresh: Refresh): Single<Access>

    @GET("test/")
    fun verifyAccessToken(@Header("authorization") access: String): Single<VerifyToken>

    @GET("api/restaurants/query_search")
    fun searchRestaurants(
            @Header("X-Api-Key") apiKey: String,
            @Header("authorization") access: String,
            @Query("query") query: String,
            @Query("page") page: Int?
    ): Single<Results>
}