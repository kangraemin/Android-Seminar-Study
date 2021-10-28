package com.clonecodingbm.data.local.login

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    val userId: String,
    val autoLogin: Boolean,
    val refresh: String,
    val access: String
) {
    @PrimaryKey
    var id: Int = 0
}