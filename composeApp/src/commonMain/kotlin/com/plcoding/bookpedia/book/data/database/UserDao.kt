package com.plcoding.bookpedia.book.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Upsert
    suspend fun upsert(user: UserEntity)

    @Query("SELECT * FROM UserEntity")
    suspend fun getAllUser(): List<UserEntity>

    @Query("SELECT * FROM UserEntity WHERE email= :email")
    suspend fun login(email: String): UserEntity?

    @Query("SELECT * FROM UserEntity Where isLogin=true ")
    suspend fun selectUserLogin(): UserEntity?

    @Query("UPDATE UserEntity SET isLogin = true WHERE email = :email")
    suspend fun updateUserLogin(email: String)

    @Query("UPDATE UserEntity SET isLogin = false WHERE email = :email")
    suspend fun logOut(email: String)



}