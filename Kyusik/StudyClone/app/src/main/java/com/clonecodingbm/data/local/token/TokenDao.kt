package com.clonecodingbm.data.local.token

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface TokenDao {
    @Query(value = "SELECT * FROM TokenEntity")
    fun getTokens(): Single<TokenEntity>

    @Insert(onConflict = REPLACE)
    fun saveToken(tokenEntity : TokenEntity): Completable

    @Query(value = "DELETE FROM TokenEntity")
    fun deleteToken(): Completable

    @Query(value = "SELECT access FROM TokenEntity")
    fun getAccessToken(): Single<String>
}