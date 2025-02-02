package com.plcoding.bookpedia.book.data.database

import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor

expect class DataBaseFactory {

    fun create():RoomDatabase.Builder<FavouriteBookDatabase>

}