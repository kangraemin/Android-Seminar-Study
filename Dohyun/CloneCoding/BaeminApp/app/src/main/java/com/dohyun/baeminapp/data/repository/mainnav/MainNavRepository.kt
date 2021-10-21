package com.dohyun.baeminapp.data.repository.mainnav

import com.dohyun.baeminapp.data.entity.Token
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface MainNavRepository {
    fun existToken(): Single<Token>?
    fun checkNetwork(): Completable
    fun verifyToken(access: String): Completable
    fun updateToken(refresh: String): Single<String>
    fun saveUpdates(token: Token): Completable
    fun logout(): Completable
}