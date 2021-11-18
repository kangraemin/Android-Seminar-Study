package com.terry.delivery.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/*
 * Created by Taehyung Kim on 2021-11-09
 */
@Entity(
    tableName = "search_history",
    indices = [Index(value = ["title"], unique = true)]
)
data class SearchHistory(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    @ColumnInfo(name = "title")
    val title: String
)
