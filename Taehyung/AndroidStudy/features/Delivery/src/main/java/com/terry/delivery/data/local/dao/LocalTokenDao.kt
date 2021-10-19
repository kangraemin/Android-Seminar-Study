package com.terry.delivery.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.terry.delivery.data.local.model.LocalToken
import io.reactivex.Completable
import io.reactivex.Single

/*
 * Created by Taehyung Kim on 2021-09-28
 */
@Dao
interface LocalTokenDao {

    @Query("SELECT * FROM local_token")
    fun getLocalToken(): Single<LocalToken>

    @Insert
    fun saveTokens(localToken: LocalToken): Completable

    @Update
    fun updateAccessToken(localToken: LocalToken): Completable

    @Query("DELETE FROM local_token")
    fun deleteAllTokens(): Completable
}