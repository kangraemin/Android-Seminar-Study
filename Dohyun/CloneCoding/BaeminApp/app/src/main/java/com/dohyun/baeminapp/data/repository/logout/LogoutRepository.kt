package com.dohyun.baeminapp.data.repository.logout

import io.reactivex.rxjava3.core.Completable

interface LogoutRepository {
    fun logout(): Completable
}