package com.dohyun.baeminapp.data.repository.login

import com.dohyun.baeminapp.data.entity.UserInfo
import com.dohyun.baeminapp.data.local.TokenDao
import com.dohyun.baeminapp.data.remote.ApiService
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
        private val tokenDao: TokenDao,
        private val apiService: ApiService
) : LoginRepository {
    override fun login(user: UserInfo): Completable {
        return Completable.create { emitter ->
            apiService.login(user)
                .subscribeOn(Schedulers.io())
                .subscribe({ token ->
                    tokenDao.saveToken(token)
                            .subscribeOn(Schedulers.io())
                            .subscribe({
                                emitter.onComplete()
                            }, {
                                println("Login RepositoryImpl save token error $it")
                                emitter.onError(it)
                            })
                }, { throwable ->
                    println("Login RepositoryImpl login error $throwable")
                    emitter.onError(throwable)
                })
        }
    }
}