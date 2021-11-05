package com.dohyun.baeminapp.data.repository.mainnav

import com.dohyun.baeminapp.data.repository.token.TokenService
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class MainNavRepositoryImpl @Inject constructor(
    private val tokenService: TokenService
): MainNavRepository {
    override fun verifyToken(): Completable {
        return tokenService.checkAccessToken()
    }

    override fun updateToken(): Completable {
        return tokenService.updateAccessToken()
    }

}