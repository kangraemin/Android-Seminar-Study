package com.dohyun.baeminapp.data.repository.logout

import com.dohyun.baeminapp.data.local.TokenDao
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class LogoutRepositoryImpl @Inject constructor(
    private val tokenDao: TokenDao
): LogoutRepository {

    override fun logout(): Completable {
        return tokenDao.deleteToken()
    }
}