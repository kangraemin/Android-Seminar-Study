package com.clonecodingbm.data.repository.login

import com.clonecodingbm.data.remote.login.LoginRequest
import com.clonecodingbm.data.local.token.TokenDataSource
import com.clonecodingbm.data.local.token.TokenMapper.mappingRemoteDataToLocal
import com.clonecodingbm.data.remote.login.LoginDataSource
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val tokenDataSource: TokenDataSource,
    private val loginDataSource: LoginDataSource
) : LoginRepository {
    override fun login(loginRequest: LoginRequest): Completable {
        return loginDataSource
            .login(loginRequest)
            .flatMapCompletable { tokenEntity ->
                tokenDataSource
                    .deleteToken()
                    .andThen(tokenDataSource.saveToken(mappingRemoteDataToLocal(tokenEntity)))
            }
    }
}