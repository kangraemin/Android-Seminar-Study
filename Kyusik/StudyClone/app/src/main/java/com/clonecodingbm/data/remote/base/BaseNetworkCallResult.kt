package com.clonecodingbm.data.remote.base

data class BaseNetworkCallResult<T>(
    val result: T? = null,
    val throwable: Throwable? = null
)