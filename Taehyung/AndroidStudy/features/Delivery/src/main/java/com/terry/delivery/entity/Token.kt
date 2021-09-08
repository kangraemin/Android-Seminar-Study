package com.terry.delivery.entity

data class Token(
    val access: String?,
    val refresh: String?,
    val detail: String?
)