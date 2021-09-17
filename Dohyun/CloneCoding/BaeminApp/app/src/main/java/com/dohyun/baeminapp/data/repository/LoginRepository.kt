package com.dohyun.baeminapp.data.repository

import com.dohyun.baeminapp.data.local.login.LoginLocalDataSource
import com.dohyun.baeminapp.data.remote.login.LoginRemoteDataSource
import com.dohyun.baeminapp.data.entity.Token
import com.dohyun.baeminapp.data.entity.UserInfo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class LoginRepository constructor(
    private val loginLocalDataSource: LoginLocalDataSource,
    private val loginRemoteDataSource: LoginRemoteDataSource
) {

    fun login(user: UserInfo): Single<Token> {
        return loginRemoteDataSource
            .login(user)
    }

    fun saveTokens(token: Token): Completable {
        return loginLocalDataSource.saveTokens(token)
    }
}