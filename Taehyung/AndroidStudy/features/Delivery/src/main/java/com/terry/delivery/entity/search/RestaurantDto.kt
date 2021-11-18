package com.terry.delivery.entity.search

data class RestaurantDto(
    val full_address: String,
    val id: Int,
    val lat: String,
    val lng: String,
    val name: String
)