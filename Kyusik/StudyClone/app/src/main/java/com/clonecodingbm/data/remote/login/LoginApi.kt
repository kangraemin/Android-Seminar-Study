package com.clonecodingbm.data.remote.login

import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginApi {
    @POST("api/token/")
    fun login(@Body loginRequest: LoginRequest) : Single<LoginResponse>

    @POST("api/token/refresh/")
    fun refreshToken(@Body refresh: String): Single<String>

    @GET("test/")
    fun checkToken(@Header("authorization") access: String): Single<CheckTokenResponse>
}