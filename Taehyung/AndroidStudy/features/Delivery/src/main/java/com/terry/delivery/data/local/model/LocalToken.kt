package com.terry.delivery.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
 * Created by Taehyung Kim on 2021-09-28
 */
@Entity(tableName = "local_token")
data class LocalToken(
    @PrimaryKey val id: Long? = 0,
    @ColumnInfo(name = "accessToken") val accessToken: String,
    @ColumnInfo(name = "refreshToken") val refreshToken: String
)