package com.example.deliveryclonecoding.data

import com.example.deliveryclonecoding.data.local.token.LocalTokenDataSource
import com.example.deliveryclonecoding.data.local.token.LocalTokenMapper.mappingRemoteDataToLocal
import com.example.deliveryclonecoding.data.remote.login.LoginDataSource
import io.reactivex.Completable
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localTokenDataSource: LocalTokenDataSource,
    private val loginDataSource: LoginDataSource
) : Repository {
    override fun login(id: String, password: String): Completable {
        return loginDataSource
            .login(id, password)
            .flatMapCompletable { loginDataItem ->
                localTokenDataSource
                    .deleteAllCachedToken()
                    .andThen(localTokenDataSource.saveToken(mappingRemoteDataToLocal(loginDataItem)))
            }
    }
}