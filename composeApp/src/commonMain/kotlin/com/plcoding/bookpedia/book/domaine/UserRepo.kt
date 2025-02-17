package com.plcoding.bookpedia.book.domaine

import androidx.room.Entity
import com.plcoding.bookpedia.book.data.database.UserEntity
import com.plcoding.bookpedia.core.domain.EmptyResult
import com.plcoding.bookpedia.core.domain.Result

interface UserRepo {
    suspend fun register(user: User):EmptyResult<DataError.Local>
    suspend fun login(email:String,password:String): Result<User?, DataError.Local>
    suspend fun getLoginUser():Result<User?,DataError.Local>
    suspend fun logout(email: String):EmptyResult<DataError.Local>

}