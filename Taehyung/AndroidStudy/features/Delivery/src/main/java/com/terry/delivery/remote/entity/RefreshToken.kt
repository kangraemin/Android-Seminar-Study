package com.terry.delivery.remote.entity

data class RefreshToken(
    val access: String?,
    val code: String?,
    val detail: String?,
    val messages: List<Message>?
)