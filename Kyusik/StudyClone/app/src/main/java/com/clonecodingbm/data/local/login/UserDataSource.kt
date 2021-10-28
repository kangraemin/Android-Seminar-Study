package com.clonecodingbm.data.local.login

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface UserDataSource {
    fun getUser(): Single<UserEntity>
    fun saveUser(userEntity : UserEntity): Completable
    fun deleteUser(): Completable
    fun getAccessToken(): Single<String>
    fun updateAccessToken(access: String): Completable
    fun isAutoLogin(): Single<Boolean>
}