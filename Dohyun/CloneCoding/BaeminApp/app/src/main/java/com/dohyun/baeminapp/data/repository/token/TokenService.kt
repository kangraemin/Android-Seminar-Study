package com.dohyun.baeminapp.data.repository.token

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface TokenService {
    fun checkAccessToken(): Completable
    fun updateAccessToken(): Completable
}