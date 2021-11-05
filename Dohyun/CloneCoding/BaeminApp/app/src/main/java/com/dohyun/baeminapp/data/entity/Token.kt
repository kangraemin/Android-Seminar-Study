package com.dohyun.baeminapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Token(
    val refresh: String,
    val access: String
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
