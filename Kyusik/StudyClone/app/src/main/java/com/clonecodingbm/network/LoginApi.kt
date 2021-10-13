package com.clonecodingbm.network

import com.clonecodingbm.data.login.Token
import com.clonecodingbm.data.login.LoginInfo
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("api/token/")
    fun login(@Body loginInfo: LoginInfo): Single<Token>
}