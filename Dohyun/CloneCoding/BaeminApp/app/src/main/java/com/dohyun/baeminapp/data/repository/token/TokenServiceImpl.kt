package com.dohyun.baeminapp.data.repository.token

import com.dohyun.baeminapp.data.entity.Refresh
import com.dohyun.baeminapp.data.entity.Token
import com.dohyun.baeminapp.data.local.TokenDao
import com.dohyun.baeminapp.data.remote.ApiService
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class TokenServiceImpl @Inject constructor(
        private val tokenDao: TokenDao,
        private val apiService: ApiService
): TokenService {
    override fun checkAccessToken(): Completable {
        return tokenDao.getAccessToken()
                .flatMapCompletable {
                    apiService.verifyAccessToken("Bearer $it")
                            .ignoreElement()
                }.doOnError {
                    println("TokenServiceImpl error ${it.message}")
                }
    }

    override fun updateAccessToken(): Completable {
        return tokenDao.getTokens()
                .flatMapCompletable {
                    apiService.getAccessToken(Refresh(it.refresh))
                            .flatMapCompletable { access ->
                                tokenDao.saveToken(Token(it.refresh, access.token))
                            }.doOnError {
                                println("TokenServiceImpl getAccessToken error ${it.message}")
                                tokenDao.deleteToken()
                                println("TokenServiceImpl logout")
                            }
                }
    }
}