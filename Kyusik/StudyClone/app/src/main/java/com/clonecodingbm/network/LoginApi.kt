package com.clonecodingbm.network

import com.clonecodingbm.db.TokenEntity
import com.clonecodingbm.model.LoginInfo
import com.clonecodingbm.model.Token
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("api/token/")
    fun login(@Body loginInfo: LoginInfo): Single<Token>
}