package com.example.deliveryclonecoding.data.remote.base

import io.reactivex.SingleTransformer

interface BaseNetworkErrorHandler {
    fun <T> applyNetworkErrorHandler(): SingleTransformer<T, T>
}