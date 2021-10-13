package com.terry.delivery.data.repository

import com.terry.delivery.data.DeliveryRepository
import com.terry.delivery.data.local.dao.LocalTokenDao
import com.terry.delivery.data.mapper.mapToLocalToken
import com.terry.delivery.data.remote.LoginApi
import com.terry.delivery.data.remote.model.LoginInfo
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-09-23
 */
class DeliveryRepositoryImpl @Inject constructor(
    private val loginApi: LoginApi,
    private val localTokenDao: LocalTokenDao
) : DeliveryRepository {

    override fun login(id: String, password: String): Completable {
        return loginApi
            .getAccessToken(LoginInfo(id, password))
            .subscribeOn(Schedulers.io())
            .flatMapCompletable { token ->
                localTokenDao
                    .deleteAllTokens()
                    .andThen(localTokenDao.saveTokens(token.mapToLocalToken()))
                    .doOnError { it.printStackTrace() }
            }
    }
}

