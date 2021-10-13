package com.clonecodingbm.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Restaurants(
    val restaurants: List<Restaurant>
)

@JsonClass(generateAdapter = true)
data class Restaurant(
    val id: Int,
    val name: String,
    val lat: String,
    val lon: String,
    @Json(name = "full_address")
    val fullAddress: String,
)