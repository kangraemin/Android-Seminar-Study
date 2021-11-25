package com.clonecodingbm.data.remote.login

import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.Header

interface LoginDataSource {
    fun login(@Body loginRequest: LoginRequest) : Single<LoginResponse>
    fun refreshToken(@Body refresh: RefreshRequest): Single<RefreshResponse>
    fun checkToken(@Header("authorization") access: String): Single<CheckTokenResponse>
}