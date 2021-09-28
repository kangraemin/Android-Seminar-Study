package com.terry.delivery.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.terry.delivery.data.model.LocalToken
import io.reactivex.Completable
import io.reactivex.Single

/*
 * Created by Taehyung Kim on 2021-09-28
 */
@Dao
interface LocalTokenDao {

    @Query("SELECT * FROM LocalToken")
    fun getLocalToken(): Single<LocalToken>

    @Insert
    fun saveTokens(localToken: LocalToken): Completable

    @Query("DELETE FROM LocalToken")
    fun deleteAllTokens(): Completable
}