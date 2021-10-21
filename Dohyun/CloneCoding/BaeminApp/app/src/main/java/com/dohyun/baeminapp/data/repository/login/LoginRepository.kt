package com.dohyun.baeminapp.data.repository.login

import com.dohyun.baeminapp.data.entity.UserInfo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single


interface LoginRepository {
    fun login(user: UserInfo): Completable
    fun isLogin(): Single<Boolean>
}