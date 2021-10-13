package com.clonecodingbm.data.login

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "token")
data class Token(
    @PrimaryKey(autoGenerate = true) val idx: Int = 0,
    @ColumnInfo(name = "accessToken") val accessToken: String,
    @ColumnInfo(name = "refreshToken") val refreshToken: String
)