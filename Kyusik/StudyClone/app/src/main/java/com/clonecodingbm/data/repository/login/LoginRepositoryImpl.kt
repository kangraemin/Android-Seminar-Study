package com.clonecodingbm.data.repository.login

import com.clonecodingbm.data.local.login.UserDataSource
import com.clonecodingbm.data.local.login.UserMapper.mappingRemoteDataToLocal
import com.clonecodingbm.data.remote.login.LoginDataSource
import com.clonecodingbm.data.remote.login.LoginRequest
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource,
    private val loginDataSource: LoginDataSource
) : LoginRepository {
    override fun login(loginRequest: LoginRequest): Completable {
        return loginDataSource
            .login(loginRequest)
            .flatMapCompletable { tokenEntity ->
                userDataSource
                    .deleteUser()
                    .andThen(
                        userDataSource.saveUser(
                            mappingRemoteDataToLocal(
                                loginRequest.username,
                                tokenEntity
                            )
                        )
                    )
            }
    }
}