package com.terry.delivery.data.repository

import com.terry.delivery.data.DeliveryRepository
import com.terry.delivery.data.remote.LoginService
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-09-23
 */
class DeliveryRepositoryImpl @Inject constructor(
    private val loginService: LoginService
) : DeliveryRepository {

    override fun login(id: String, password: String): Completable {
        return loginService
            .getAccessToken(id, password)
            .subscribeOn(Schedulers.io())
            .flatMapCompletable {
                Completable.complete()
            }
    }
}

