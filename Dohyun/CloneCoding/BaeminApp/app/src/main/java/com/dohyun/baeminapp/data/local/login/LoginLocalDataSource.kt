package com.dohyun.baeminapp.data.local.login

import com.dohyun.baeminapp.data.entity.Token
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single


interface LoginLocalDataSource {
    fun getTokens(): Single<Token>
    fun saveTokens(token: Token): Completable
    fun clearCache(): Completable
}