package com.terry.delivery.entity.search

import com.google.gson.annotations.SerializedName
import com.terry.delivery.entity.NetworkResult
import com.terry.delivery.entity.login.MessageDto

data class SearchItem(
    override val detail: String?,
    override val code: String?,
    override val message: List<MessageDto>?,
    @SerializedName("restaurants")
    val restaurantDto: List<RestaurantDto>
) : NetworkResult