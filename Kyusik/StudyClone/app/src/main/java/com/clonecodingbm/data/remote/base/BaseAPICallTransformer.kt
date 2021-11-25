package com.clonecodingbm.data.remote.base

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleTransformer

fun <T> Single<T>.wrappingAPICallResult(): Single<BaseNetworkCallResult<T>> =
    compose(wrappingSingleNetworkCallResult())

private fun <T> wrappingSingleNetworkCallResult() =
    SingleTransformer<T, BaseNetworkCallResult<T>> { single ->
        single
            .map { data -> BaseNetworkCallResult(data) }
            .onErrorReturn { BaseNetworkCallResult(throwable = it) }
    }

//private fun <T> wrappingSingle