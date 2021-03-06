package com.terry.delivery.data.remote

import com.terry.delivery.entity.RefreshToken
import com.terry.delivery.entity.Token
import com.terry.delivery.entity.VerifyToken
import io.reactivex.Single
import retrofit2.http.*

/*
 * Created by Taehyung Kim on 2021-09-08
 */
interface LoginService {

    @FormUrlEncoded
    @POST("api/token/")
    fun getAccessToken(
        @Field("username") userName: String,
        @Field("password") password: String
    ): Single<Token>

    @FormUrlEncoded
    @POST("api/token/refresh/")
    fun refreshAccessToken(
        @Field("refresh") accessToken: String
    ): Single<RefreshToken>

    @GET("test/")
    fun verifyAccessToken(
        @Header("authorization") accessToken: String
    ): Single<VerifyToken>
}