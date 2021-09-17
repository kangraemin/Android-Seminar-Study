package com.dohyun.baeminapp.data.remote.login

import com.dohyun.baeminapp.data.entity.Token
import com.dohyun.baeminapp.data.entity.UserInfo
import io.reactivex.rxjava3.core.Single

interface LoginRemoteDataSource {
    fun login(user: UserInfo): Single<Token>
}