package com.example.deliveryclonecoding.data.remote.login.datasource

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.POST

interface TokenApi {
    @POST("api/token/")
    fun getUserTokenFromServer(@Body tokenParameter: TokenParameter): Single<TokenDataItem>
    @POST("api/refresh/")
    fun refreshAccessToken(@Body refresh: String): Single<TokenDataItem>
}