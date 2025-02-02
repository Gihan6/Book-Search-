package com.plcoding.bookpedia.book.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

actual class DataBaseFactory (
    val context:Context
){
    actual fun create(): RoomDatabase.Builder<FavouriteBookDatabase> {

        val appContext=context.applicationContext
        val dbFile=appContext.getDatabasePath(FavouriteBookDatabase.DB_NAME)
        return Room.databaseBuilder(
            appContext,
            dbFile.absolutePath
        )
    }

}