package com.terry.delivery.entity

data class RefreshToken(
    val access: String?,
    val code: String?,
    val detail: String?
)