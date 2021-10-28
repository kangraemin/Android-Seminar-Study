package com.clonecodingbm.data.local.login

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface UserDao {
    @Query(value = "SELECT * FROM UserEntity")
    fun getUser(): Single<UserEntity>

    @Insert(onConflict = REPLACE)
    fun saveUser(userEntity : UserEntity): Completable

    @Query(value = "DELETE FROM UserEntity")
    fun deleteUser(): Completable

    @Query(value = "SELECT access FROM UserEntity")
    fun getAccessToken(): Single<String>

    @Query(value = "UPDATE UserEntity SET access = :access WHERE id = 0")
    fun updateAccessToken(access: String): Completable

    @Query(value = "SELECT autoLogin FROM UserEntity")
    fun isAutoLogin(): Single<Boolean>
}