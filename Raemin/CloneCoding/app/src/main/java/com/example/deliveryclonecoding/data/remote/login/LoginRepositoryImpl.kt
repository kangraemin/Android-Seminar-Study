package com.example.deliveryclonecoding.data.remote.login

import com.example.deliveryclonecoding.data.local.token.LocalTokenDataSource
import com.example.deliveryclonecoding.data.local.token.LocalTokenMapper.mappingRemoteDataToLocal
import com.example.deliveryclonecoding.data.remote.login.datasource.TokenDataSource
import io.reactivex.Completable
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val localTokenDataSource: LocalTokenDataSource,
    private val tokenDataSource: TokenDataSource
) : LoginRepository {
    override fun login(id: String, password: String): Completable {
        return tokenDataSource
            .getUserTokenFromServer(id, password)
            .flatMapCompletable { loginDataItem ->
                localTokenDataSource
                    .deleteAllCachedToken()
                    .andThen(localTokenDataSource.saveToken(mappingRemoteDataToLocal(loginDataItem)))
            }
    }

    override fun refreshToken(): Completable {
        return localTokenDataSource
            .getToken()
            .flatMap { tokenDataSource.refreshAccessToken(it.refreshToken) }
            .flatMapCompletable { localTokenDataSource.updateAccessToken(it.access) }
    }
}