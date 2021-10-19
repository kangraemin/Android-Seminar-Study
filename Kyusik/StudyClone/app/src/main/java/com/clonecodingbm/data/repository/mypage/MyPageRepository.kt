package com.clonecodingbm.data.repository.mypage

import io.reactivex.rxjava3.core.Completable

interface MyPageRepository {
    fun checkToken(): Completable
}