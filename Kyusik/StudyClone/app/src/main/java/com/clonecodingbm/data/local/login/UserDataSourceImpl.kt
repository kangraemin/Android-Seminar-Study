package com.clonecodingbm.data.local.login

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val userDao: UserDao
) : UserDataSource {
    override fun getUser(): Single<UserEntity> {
        return userDao
            .getUser()
            .subscribeOn(Schedulers.io())
    }

    override fun saveUser(userEntity: UserEntity): Completable {
        return userDao
            .saveUser(userEntity)
            .subscribeOn(Schedulers.io())
    }

    override fun deleteUser(): Completable {
        return userDao
            .deleteUser()
            .subscribeOn(Schedulers.io())
    }

    override fun getAccessToken(): Single<String> {
        return userDao
            .getAccessToken()
            .subscribeOn(Schedulers.io())
//            .doOnSuccess { throw Exception() }
//            .onErrorResumeNext {  }
            .onErrorReturn { "error" }
    }

    override fun updateAccessToken(access: String): Completable {
        return userDao
            .updateAccessToken(access)
            .subscribeOn(Schedulers.io())
    }

    override fun isLogin(): Single<String> {
        return userDao
            .isLogin()
            .subscribeOn(Schedulers.io())
    }
}