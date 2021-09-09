package com.example.deliveryclonecoding.data.remote.login

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("api/token/")
    fun login(@Body loginInfo: LoginInfo): Single<LoginDataItem>
}