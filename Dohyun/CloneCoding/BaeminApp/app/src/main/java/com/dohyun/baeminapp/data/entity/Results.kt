package com.dohyun.baeminapp.data.entity

import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("restaurants")
    val data : List<Restaurant>
)
