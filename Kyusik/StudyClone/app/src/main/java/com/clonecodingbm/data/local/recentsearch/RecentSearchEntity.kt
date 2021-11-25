package com.clonecodingbm.data.local.recentsearch

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RecentSearches")
data class RecentSearchEntity(
    @PrimaryKey
    val searchWord: String
)