package com.terry.delivery.remote.entity

/*
 * Created by Taehyung Kim on 2021-09-08
 */
data class VerifyToken(
    val detail: String?,
    val code: String?,
    val messages: List<Message>?
)
