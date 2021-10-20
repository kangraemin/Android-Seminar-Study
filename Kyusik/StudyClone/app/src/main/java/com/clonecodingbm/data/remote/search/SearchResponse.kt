package com.clonecodingbm.data.remote.search

data class SearchResponse(
    val restaurants: List<Restaurant>
)

data class Restaurant(
    val id: Int,
    val name: String,
    val lat: String,
    val lng: String,
    val fullAddress: String,
)