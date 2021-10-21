package com.example.deliveryclonecoding.data.remote.login.datasource

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface TokenApi {
    @POST("api/token/")
    fun getUserTokenFromServer(@Body tokenParameter: TokenParameter): Single<TokenDataItem>
    @POST("api/token/refresh/")
    fun refreshAccessToken(@Body refreshTokenParameter: RefreshTokenParameter): Single<TokenDataItem>
}