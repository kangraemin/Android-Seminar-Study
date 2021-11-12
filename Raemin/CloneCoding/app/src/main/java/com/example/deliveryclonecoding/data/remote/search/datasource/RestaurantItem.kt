package com.example.deliveryclonecoding.data.remote.search.datasource

data class RestaurantItem(
    val id: Int,
    val name: String,
    val lat: String,
    val lng: String,
    val full_address: String
)