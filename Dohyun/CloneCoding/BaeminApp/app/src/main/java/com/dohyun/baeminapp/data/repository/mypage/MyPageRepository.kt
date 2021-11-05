package com.dohyun.baeminapp.data.repository.mypage

import io.reactivex.rxjava3.core.Completable

interface MyPageRepository {
    fun checkTokens(): Completable
    fun updateTokens(): Completable
}