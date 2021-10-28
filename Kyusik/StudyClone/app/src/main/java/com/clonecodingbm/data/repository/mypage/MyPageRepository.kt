package com.clonecodingbm.data.repository.mypage

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface MyPageRepository {
    fun checkToken(): Completable
    fun isAutoLogin(): Single<Boolean>
}