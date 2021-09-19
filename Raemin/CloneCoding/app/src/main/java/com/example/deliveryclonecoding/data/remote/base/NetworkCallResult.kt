package com.example.deliveryclonecoding.data.remote.base

data class NetworkCallResult<T>(
    val data : T? = null,
    val throwable: Throwable? = null
)