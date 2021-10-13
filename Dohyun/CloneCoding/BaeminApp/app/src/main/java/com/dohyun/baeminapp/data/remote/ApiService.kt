package com.dohyun.baeminapp.data.remote

import com.dohyun.baeminapp.data.entity.Token
import com.dohyun.baeminapp.data.entity.UserInfo
import com.dohyun.baeminapp.data.entity.VerifyToken
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface ApiService {

    @POST("api/token/")
    fun login(@Body user: UserInfo) : Single<Token>

    @POST("api/token/refresh/")
    fun getAccessToken(@Body refresh: String): Single<String>

    @GET("test/")
    fun verifyAccessToken(@Header("authorization") access: String): Single<VerifyToken>
}