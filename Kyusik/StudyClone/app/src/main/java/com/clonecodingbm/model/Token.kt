package com.clonecodingbm.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Token (
    val access: String,
    val refresh: String
)