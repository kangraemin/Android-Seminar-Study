package com.clonecodingbm.repository

import com.clonecodingbm.data.login.LoginInfo
import com.clonecodingbm.data.login.Token
import com.clonecodingbm.network.LoginApi
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val loginApi: LoginApi
) {
    fun login(id: String, password: String): Single<Token> {
        return loginApi
            .login(LoginInfo(id, password))
            .subscribeOn(Schedulers.io())
    }
}