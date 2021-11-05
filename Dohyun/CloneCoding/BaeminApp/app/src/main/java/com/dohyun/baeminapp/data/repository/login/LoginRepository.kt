package com.dohyun.baeminapp.data.repository.login

import com.dohyun.baeminapp.data.entity.UserInfo
import io.reactivex.rxjava3.core.Completable


interface LoginRepository {
    fun login(user: UserInfo): Completable
}