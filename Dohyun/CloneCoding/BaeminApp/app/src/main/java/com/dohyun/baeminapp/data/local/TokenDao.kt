package com.dohyun.baeminapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dohyun.baeminapp.data.entity.Token
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface TokenDao {

    @Query(value = "SELECT * FROM token LIMIT 1")
    fun getToken(): Single<Token>

    @Insert
    fun saveToken(token : Token): Completable

    @Query(value = "DELETE FROM token")
    fun clearCache(): Completable
}