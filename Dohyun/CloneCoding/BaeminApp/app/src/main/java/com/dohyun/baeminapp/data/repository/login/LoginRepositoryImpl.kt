package com.dohyun.baeminapp.data.repository.login

import com.dohyun.baeminapp.data.entity.Token
import com.dohyun.baeminapp.data.entity.UserInfo
import com.dohyun.baeminapp.data.local.TokenDao
import com.dohyun.baeminapp.data.remote.ApiService
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val tokenDao: TokenDao,
    private val apiService: ApiService
): LoginRepository {

    override fun login(user: UserInfo): Single<Token> {
        return apiService.login(user)
    }

    override fun saveTokens(token: Token): Completable {
        return tokenDao.saveToken(token)
    }
}