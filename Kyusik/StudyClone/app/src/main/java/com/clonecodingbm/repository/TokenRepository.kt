package com.clonecodingbm.repository

import com.clonecodingbm.db.TokenEntity
import com.clonecodingbm.db.TokenDao
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class TokenRepository @Inject constructor(
    private val tokenDao: TokenDao
) {
    fun getToken(): Single<TokenEntity> {
        return tokenDao
            .getToken()
            .subscribeOn(Schedulers.io())
    }
    fun saveToken(tokenItem: TokenEntity): Completable {
        return tokenDao
            .saveToken(tokenItem)
            .subscribeOn(Schedulers.io())
    }
    fun deleteAllCachedToken(): Completable {
        return tokenDao
            .deleteAllCachedToken()
            .subscribeOn(Schedulers.io())
    }
}