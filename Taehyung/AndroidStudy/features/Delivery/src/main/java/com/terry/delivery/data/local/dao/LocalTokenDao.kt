package com.terry.delivery.data.local.dao

import androidx.room.*
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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTokens(localToken: LocalToken): Completable

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateAccessToken(localToken: LocalToken): Completable

    @Query("DELETE FROM local_token")
    fun deleteAllTokens(): Completable
}