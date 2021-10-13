package com.dohyun.baeminapp.data.repository.mypage

import com.dohyun.baeminapp.data.local.TokenDao
import com.dohyun.baeminapp.data.remote.ApiService
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MyPageRepositoryImpl @Inject constructor(
    private val tokenDao: TokenDao,
    private val apiService: ApiService
) : MyPageRepository {

    override fun verifyToken(): Completable {
        return Completable.create { emiiter ->
            tokenDao.getAccessToken()
                .observeOn(Schedulers.io())
                .subscribe({ access ->
                    apiService.verifyAccessToken("Bearer $access")
                        .observeOn(Schedulers.io())
                        .subscribe({
                            println("MyPageRepositoryImpl verifyToken $it")
                            emiiter.onComplete()
                        }, {
                            println("MyPageRepositoryImpl verifyToken error $it")
                            emiiter.onError(it)
                        })
                }, { throwable ->
                    println("MyPageRepositoryImpl getAccessToken error $throwable")
                    emiiter.onError(throwable)
                })
        }
    }
}