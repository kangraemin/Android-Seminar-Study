package com.dohyun.baeminapp.data.remote.login

import com.dohyun.baeminapp.data.entity.Token
import com.dohyun.baeminapp.data.entity.UserInfo
import com.dohyun.baeminapp.data.remote.ApiService
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class LoginRemoteDataSourceImpl(
    private val apiService: ApiService
) : LoginRemoteDataSource {
    override fun login(user: UserInfo): Single<Token> {
        return apiService.login(user)
            .subscribeOn(Schedulers.io())
    }
}