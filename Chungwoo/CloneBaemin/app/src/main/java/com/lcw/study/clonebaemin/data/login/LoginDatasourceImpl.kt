package com.lcw.study.clonebaemin.data.login

import com.lcw.study.clonebaemin.data.ApiService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class LoginDatasourceImpl(private val apiService: ApiService) : LoginDatasource {

    override fun login(id: String, password: String): Single<LoginData> {
        return apiService
            .requestLogin(RequestLoginInfoData(id,password))
            .subscribeOn(Schedulers.io())
    }
}