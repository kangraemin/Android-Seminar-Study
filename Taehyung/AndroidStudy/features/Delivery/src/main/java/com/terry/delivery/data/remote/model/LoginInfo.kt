package com.terry.delivery.data.remote.model

import com.google.gson.annotations.SerializedName

/*
 * Created by Taehyung Kim on 2021-10-13
 */
data class LoginInfo(
    @SerializedName("username")
    val userName: String,
    val password: String
)
