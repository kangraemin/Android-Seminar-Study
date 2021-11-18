package com.terry.delivery.entity.login

data class Token(
    val access: String?,
    val refresh: String?,
    val detail: String?
)