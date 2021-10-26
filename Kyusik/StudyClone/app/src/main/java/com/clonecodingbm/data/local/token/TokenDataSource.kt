package com.clonecodingbm.data.local.token

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface TokenDataSource {
    fun getTokens(): Single<TokenEntity>
    fun saveToken(tokenEntity : TokenEntity): Completable
    fun deleteToken(): Completable
    fun getAccessToken(): Single<String>
    fun updateAccessToken(access: String): Completable
}