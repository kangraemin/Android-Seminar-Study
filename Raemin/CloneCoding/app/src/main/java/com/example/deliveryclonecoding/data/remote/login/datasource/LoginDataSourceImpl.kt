package com.example.deliveryclonecoding.data.remote.login.datasource

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginDataSourceImpl @Inject constructor(
    private val loginApi: LoginApi
) : LoginDataSource {
    override fun login(id: String, password: String): Single<LoginDataItem> {
        return loginApi
            .login(LoginInfo(id, password))
            .subscribeOn(Schedulers.io())
    }
}