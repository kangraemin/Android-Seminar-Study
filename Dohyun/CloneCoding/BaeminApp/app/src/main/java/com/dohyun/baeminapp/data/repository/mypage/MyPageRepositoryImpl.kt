package com.dohyun.baeminapp.data.repository.mypage

import com.dohyun.baeminapp.data.repository.token.TokenService
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class MyPageRepositoryImpl @Inject constructor(
    private val tokenService: TokenService
) : MyPageRepository {
    override fun checkTokens(): Completable {
        return tokenService.checkAccessToken()
    }

    override fun updateTokens(): Completable {
        return tokenService.updateAccessToken()
    }


}