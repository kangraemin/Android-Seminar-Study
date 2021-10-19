package com.clonecodingbm.data.repository.mypage

import com.clonecodingbm.data.local.token.TokenDataSource
import com.clonecodingbm.data.remote.login.LoginDataSource
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class MyPageRepositoryImpl @Inject constructor(
    private val tokenDataSource: TokenDataSource,
    private val loginDataSource: LoginDataSource
) : MyPageRepository {
    override fun checkToken(): Completable {
        return tokenDataSource
            .getAccessToken()
            .flatMapCompletable { access ->
                loginDataSource
                    .checkToken("Bearer $access")
                    .ignoreElement()
            }
    }

    fun refreshToken(): Completable {
        return tokenDataSource
            .getTokens()
            .flatMapCompletable { token ->
                loginDataSource
                    .refreshToken(token.refresh)
                    .ignoreElement()
            }
    }
}