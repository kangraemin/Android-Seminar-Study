package com.dohyun.baeminapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dohyun.baeminapp.data.entity.Token
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface TokenDao {

    @Query(value = "SELECT * FROM Token")
    fun getTokens(): Single<Token>

    @Insert
    fun saveToken(token : Token): Completable

    @Query(value = "DELETE FROM Token")
    fun deleteToken(): Completable

    @Query(value = "SELECT access FROM Token")
    fun getAccessToken(): Single<String>

    @Update
    fun updateAccessToken(token: Token): Completable
}