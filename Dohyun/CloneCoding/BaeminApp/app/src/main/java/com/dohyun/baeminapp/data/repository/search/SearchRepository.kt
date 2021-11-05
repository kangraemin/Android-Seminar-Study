package com.dohyun.baeminapp.data.repository.search

import com.dohyun.baeminapp.data.entity.Results
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single


interface SearchRepository {
    fun search(query: String, page: Int?) : Single<Results>
    fun checkToken(): Completable
    fun updateToken(): Completable
}