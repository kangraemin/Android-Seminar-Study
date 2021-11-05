package com.dohyun.baeminapp.data.repository.mainnav

import io.reactivex.rxjava3.core.Completable

interface MainNavRepository {
    fun verifyToken(): Completable
    fun updateToken(): Completable
}