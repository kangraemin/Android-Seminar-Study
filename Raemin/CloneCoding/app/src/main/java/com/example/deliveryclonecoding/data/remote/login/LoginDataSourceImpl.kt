package com.example.deliveryclonecoding.data.remote.login

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class LoginDataSourceImpl(
    private val loginApi: LoginApi
) : LoginDataSource {
    override fun login(id: String, password: String): Single<LoginDataItem> {
        return loginApi
            .login(LoginInfo(id, password))
            .subscribeOn(Schedulers.io())
    }
}