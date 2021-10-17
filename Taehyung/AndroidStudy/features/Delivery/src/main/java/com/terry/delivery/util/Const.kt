package com.terry.delivery.util

/*
 * Created by Taehyung Kim on 2021-10-14
 */
object Const {

    const val BASE_URL =
        "https://r5670326j8.execute-api.ap-northeast-2.amazonaws.com/delivery_server/"

    private const val SEARCH_API_KEY = "iGR3hRvQ.K3wPNmJgqSMeY6CehCZmuq7Kg5Hnw3o7"

    fun getSearchApiHeaders(
        token: String,
        apiKey: String = SEARCH_API_KEY
    ) = hashMapOf(
        "Authorization" to "Bearer $token",
        "X-Api-Key" to apiKey
    )
}