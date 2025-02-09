package com.plcoding.bookpedia.book.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [BookEntity::class,UserEntity::class],
    version = 1
)
@TypeConverters(StringListTypeConverter::class)
@ConstructedBy(BookDatabaseConstructor::class)
abstract class FavouriteBookDatabase :RoomDatabase(){
    abstract val favouriteBookDao:FavouriteBookDao
    abstract val userDao:UserDao

    companion object{
       const val DB_NAME="book.db"
    }
}