package com.clonecodingbm.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginInfo (
    var username: String,
    var password: String
)