package com.terry.delivery.util

import com.terry.delivery.BuildConfig

/*
 * Created by Taehyung Kim on 2021-10-14
 */
object Const {

    const val BASE_URL =
        "https://r5670326j8.execute-api.ap-northeast-2.amazonaws.com/delivery_server/"

    fun getSearchApiHeaders(
        token: String,
        apiKey: String = BuildConfig.searchApiKey
    ) = hashMapOf(
        "Authorization" to "Bearer $token",
        "X-Api-Key" to apiKey
    )
}