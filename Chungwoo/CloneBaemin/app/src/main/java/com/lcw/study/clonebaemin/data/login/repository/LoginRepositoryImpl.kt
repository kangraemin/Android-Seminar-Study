package com.lcw.study.clonebaemin.data.login.repository

import com.lcw.study.clonebaemin.data.login.remote.LoginDataSource
import io.reactivex.Completable
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginDataSource: LoginDataSource): LoginRepository {
    override fun requestLogin(username: String, password: String): Completable {
        TODO("Not yet implemented")
    }

}