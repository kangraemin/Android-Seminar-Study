package com.dohyun.baeminapp.data.entity

import com.google.gson.annotations.SerializedName

data class Restaurant(
        @SerializedName("id")
        val id : Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("lat")
        val lat: String,
        @SerializedName("lng")
        val lng: String,
        @SerializedName("full_address")
        val full_address: String
)
