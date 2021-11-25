package com.clonecodingbm.data.repository.mypage

import com.clonecodingbm.data.local.login.UserDataSource
import com.clonecodingbm.data.remote.login.LoginDataSource
import com.clonecodingbm.data.remote.login.RefreshRequest
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MyPageRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource,
    private val loginDataSource: LoginDataSource
) : MyPageRepository {
    override fun checkToken(): Completable {
        return userDataSource
            .getAccessToken()
            .flatMapCompletable { access ->
                loginDataSource
                    .checkToken("Bearer $access")
                    .retryWhen { error ->
                        return@retryWhen error
                            .flatMapSingle {
                                return@flatMapSingle  userDataSource
                                    .getUser()
                                    .flatMap { user ->
                                        loginDataSource
                                            .refreshToken(RefreshRequest(user.refresh))
                                    }
                                    .flatMap { token ->
                                        userDataSource
                                            .updateAccessToken(token.access)
                                            .andThen(Single.just(Unit))
                                    }
                            }
                    }
                    .ignoreElement()
            }
    }

    override fun isLogin(): Single<String> {
        return userDataSource
            .isLogin()
    }

    override fun logout(): Completable {
        return userDataSource
            .deleteUser()
    }
}