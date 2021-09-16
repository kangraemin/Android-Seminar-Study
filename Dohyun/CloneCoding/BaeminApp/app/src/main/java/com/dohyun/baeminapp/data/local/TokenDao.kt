package com.dohyun.baeminapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dohyun.baeminapp.data.entity.Token
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface TokenDao {

    @Query(value = "SELECT * FROM token")
    fun getToken(): Single<Token>

    @Insert
    fun saveToken(token : Token): Completable

    @Query(value = "DELETE FROM token")
    fun clearCache(): Completable
}