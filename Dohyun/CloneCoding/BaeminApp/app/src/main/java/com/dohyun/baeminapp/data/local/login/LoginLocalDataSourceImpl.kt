package com.dohyun.baeminapp.data.local.login

import com.dohyun.baeminapp.data.entity.Token
import com.dohyun.baeminapp.data.local.TokenDao
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class LoginLocalDataSourceImpl(
    private val tokenDao: TokenDao
): LoginLocalDataSource {
    override fun getTokens(): Single<Token> {
        return tokenDao.getToken().subscribeOn(Schedulers.io())
    }

    override fun saveTokens(token: Token): Completable {
        return tokenDao.saveToken(token).subscribeOn(Schedulers.io())
    }

    override fun clearCache(): Completable {
        return tokenDao.clearCache().subscribeOn(Schedulers.io())
    }
}