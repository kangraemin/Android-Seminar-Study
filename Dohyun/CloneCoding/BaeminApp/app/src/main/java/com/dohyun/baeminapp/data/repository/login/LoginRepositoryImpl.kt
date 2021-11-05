package com.dohyun.baeminapp.data.repository.login

import com.dohyun.baeminapp.data.entity.UserInfo
import com.dohyun.baeminapp.data.local.TokenDao
import com.dohyun.baeminapp.data.remote.ApiService
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
        private val tokenDao: TokenDao,
        private val apiService: ApiService
) : LoginRepository {
    override fun login(user: UserInfo): Completable {
        return apiService.login(user)
                .flatMapCompletable {
                    tokenDao.saveToken(it)
                }
    }

}