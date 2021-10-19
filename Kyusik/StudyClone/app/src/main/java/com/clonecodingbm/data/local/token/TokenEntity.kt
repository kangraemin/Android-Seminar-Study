package com.clonecodingbm.data.local.token

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TokenEntity(
    val refresh: String,
    val access: String
) {
    @PrimaryKey
    var id: Int = 0
}