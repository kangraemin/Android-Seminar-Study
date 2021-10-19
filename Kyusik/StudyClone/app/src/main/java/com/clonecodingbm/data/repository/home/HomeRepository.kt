package com.clonecodingbm.data.repository.home

import io.reactivex.rxjava3.core.Completable

interface HomeRepository {
    fun myAddress(): Completable
}