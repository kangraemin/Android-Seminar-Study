package com.terry.delivery.remote.entity

data class Token(
    val access: String?,
    val refresh: String?,
    val detail: String?
)