package com.plcoding.bookpedia.book.domaine

import androidx.room.Entity
import com.plcoding.bookpedia.book.data.database.UserEntity
import com.plcoding.bookpedia.core.domain.EmptyResult
import com.plcoding.bookpedia.core.domain.Result
import kotlinx.coroutines.flow.Flow

interface BookRepo {

   suspend fun searchBook(query:String):Result<List<Book>,DataError.Remote>
   suspend fun getBookDescription(bookId:String):Result<String?,DataError.Remote>

   suspend fun getFavouriteBook():Flow<List<Book>>
   suspend fun ifBookFavourite(id:String):Flow<Boolean>

   suspend fun markAsFavourite(book:Book):EmptyResult<DataError.Local>
   suspend fun deleteFavourite(id:String)


}