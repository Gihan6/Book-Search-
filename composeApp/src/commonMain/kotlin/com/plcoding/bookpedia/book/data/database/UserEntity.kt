package com.plcoding.bookpedia.book.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = false) val email:String,
    val name :String,
    val phone :String,
    val password:String,
    val isLogin:Boolean

)
