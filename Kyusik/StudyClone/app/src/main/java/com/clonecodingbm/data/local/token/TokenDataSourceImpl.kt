package com.clonecodingbm.data.local.token

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class TokenDataSourceImpl @Inject constructor(
    private val tokenDao: TokenDao
) : TokenDataSource {
    override fun getTokens(): Single<TokenEntity> {
        return tokenDao
            .getTokens()
            .subscribeOn(Schedulers.io())
    }

    override fun saveToken(tokenEntity: TokenEntity): Completable {
        return tokenDao
            .saveToken(tokenEntity)
            .subscribeOn(Schedulers.io())
    }

    override fun deleteToken(): Completable {
        return tokenDao
            .deleteToken()
            .subscribeOn(Schedulers.io())
    }

    override fun getAccessToken(): Single<String> {
        return tokenDao
            .getAccessToken()
            .subscribeOn(Schedulers.io())
    }
}