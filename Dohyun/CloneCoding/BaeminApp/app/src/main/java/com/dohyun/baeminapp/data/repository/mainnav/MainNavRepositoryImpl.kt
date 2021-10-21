package com.dohyun.baeminapp.data.repository.mainnav

import com.dohyun.baeminapp.data.entity.Token
import com.dohyun.baeminapp.data.local.PreferenceManager
import com.dohyun.baeminapp.data.local.TokenDao
import com.dohyun.baeminapp.data.remote.ApiService
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainNavRepositoryImpl @Inject constructor(
    private val tokenDao: TokenDao,
    private val apiService: ApiService,
    private val preferenceManager: PreferenceManager
): MainNavRepository {

    override fun existToken(): Single<Token>? {
        return tokenDao.getTokens() ?: null
    }

    override fun checkNetwork(): Completable {
        return Completable.create {
            it.onComplete()
            // TODO: 네트워크 연결 여부 확인하기
        }
    }

    override fun verifyToken(access: String): Completable {
        return Completable.create { emitter ->
            apiService.verifyAccessToken("Bearer $access")
                .observeOn(Schedulers.io())
                .subscribe({
                    println("MainNavRepositoryImpl verifyToken :: $it")
                    emitter.onComplete()
                }, {
                    emitter.onError(it)
                })
        }
    }

    override fun updateToken(refresh: String): Single<String> {
        return apiService.getAccessToken(refresh)
    }

    override fun saveUpdates(token: Token): Completable {
        preferenceManager.checkLogin = true
        return tokenDao.updateAccessToken(token)
    }

    override fun logout(): Completable {
        preferenceManager.checkLogin = false
        return tokenDao.deleteToken()
    }

}