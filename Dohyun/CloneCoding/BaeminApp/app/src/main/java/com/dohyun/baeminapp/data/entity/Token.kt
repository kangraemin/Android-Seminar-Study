package com.dohyun.baeminapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Token(
    @PrimaryKey val uid: Int?,
    val refresh: String,
    val access: String
)
