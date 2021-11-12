package com.example.deliveryclonecoding.data.remote.base

import io.reactivex.Single
import io.reactivex.SingleTransformer

fun <T> Single<T>.wrappingAPICallResult(): Single<BaseNetworkCallResult<T>> =
    compose(wrappingSingleNetworkCallResult())

private fun <T> wrappingSingleNetworkCallResult() =
    SingleTransformer<T, BaseNetworkCallResult<T>> { single ->
        single
            .map { data -> BaseNetworkCallResult(data) }
            .onErrorReturn { BaseNetworkCallResult(throwable = it) }
    }