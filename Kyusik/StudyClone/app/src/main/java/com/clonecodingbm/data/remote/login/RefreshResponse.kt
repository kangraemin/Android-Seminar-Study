package com.clonecodingbm.data.remote.login

data class RefreshResponse(
    val access: String,
    val detail: String,
    val code: String,
)
