package com.dohyun.baeminapp.data.entity

import com.google.gson.annotations.SerializedName

data class Access(
        @SerializedName("access")
        val token : String
)
