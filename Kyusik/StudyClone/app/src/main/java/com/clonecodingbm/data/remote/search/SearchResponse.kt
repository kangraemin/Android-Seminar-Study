package com.clonecodingbm.data.remote.search

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    val restaurants: List<Restaurant>
)

data class Restaurant(
    val id: Int,
    val name: String,
    val lat: String,
    val lng: String,
    @SerializedName("full_address")
    val fullAddress: String,
)