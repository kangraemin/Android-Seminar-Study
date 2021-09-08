package com.terry.delivery.remote

import com.terry.delivery.remote.entity.RefreshToken
import com.terry.delivery.remote.entity.Token
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

    @GET("api/token/refresh/")
    fun refreshAccessToken(
        @Header("Authorization") accessToken: String
    ): Single<RefreshToken>

}