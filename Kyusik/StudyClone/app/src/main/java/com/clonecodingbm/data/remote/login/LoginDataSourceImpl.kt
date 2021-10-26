package com.clonecodingbm.data.remote.login

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class LoginDataSourceImpl @Inject constructor(
    private val loginApi: LoginApi
) : LoginDataSource {
    override fun login(loginRequest: LoginRequest): Single<LoginResponse> {
        return loginApi
            .login(loginRequest)
            .subscribeOn(Schedulers.io())
    }

    override fun refreshToken(refresh: RefreshRequest): Single<RefreshResponse> {
        return loginApi
            .refreshToken(refresh)
            .subscribeOn(Schedulers.io())
    }

    override fun checkToken(access: String): Single<CheckTokenResponse> {
        return loginApi
            .checkToken(access)
            .subscribeOn(Schedulers.io())
    }

}