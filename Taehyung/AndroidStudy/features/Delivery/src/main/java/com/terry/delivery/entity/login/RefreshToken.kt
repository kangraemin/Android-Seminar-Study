package com.terry.delivery.entity.login

data class RefreshToken(
    val access: String?,
    val code: String?,
    val detail: String?
)