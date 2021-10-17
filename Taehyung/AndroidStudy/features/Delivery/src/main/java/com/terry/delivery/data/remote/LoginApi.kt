package com.terry.delivery.data.remote

import com.terry.delivery.data.remote.model.LoginInfo
import com.terry.delivery.entity.login.RefreshToken
import com.terry.delivery.entity.login.Token
import com.terry.delivery.entity.login.VerifyToken
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

/*
 * Created by Taehyung Kim on 2021-09-08
 */
interface LoginApi {

    @POST("api/token/")
    fun getAccessToken(
        @Body loginInfo: LoginInfo
    ): Single<Token>

    @FormUrlEncoded
    @POST("api/token/refresh/")
    fun refreshAccessToken(
        @Field("refresh") accessToken: String
    ): Single<Response<RefreshToken>>

    @GET("test/")
    fun verifyAccessToken(
        @Header("Authorization") accessToken: String
    ): Single<Response<VerifyToken>>
}