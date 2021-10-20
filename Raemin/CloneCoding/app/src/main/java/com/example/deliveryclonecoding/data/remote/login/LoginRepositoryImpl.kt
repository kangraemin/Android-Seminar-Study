package com.example.deliveryclonecoding.data.remote.login

import com.example.deliveryclonecoding.data.local.token.LocalTokenDataSource
import com.example.deliveryclonecoding.data.local.token.LocalTokenMapper.mappingRemoteDataToLocal
import com.example.deliveryclonecoding.data.remote.login.datasource.LoginDataSource
import com.example.deliveryclonecoding.data.remote.login.LoginRepository
import io.reactivex.Completable
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val localTokenDataSource: LocalTokenDataSource,
    private val loginDataSource: LoginDataSource
) : LoginRepository {
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