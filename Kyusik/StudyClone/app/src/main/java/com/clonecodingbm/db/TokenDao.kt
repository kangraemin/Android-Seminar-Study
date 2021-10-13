package com.clonecodingbm.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.clonecodingbm.data.login.Token
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface TokenDao {
    @Query(value = "SELECT * FROM token LIMIT 1")
    fun getToken(): Single<Token>

    @Insert
    fun saveToken(tokenItem: Token): Completable

    @Query(value = "DELETE FROM token")
    fun deleteAllCachedToken(): Completable
}