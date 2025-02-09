package com.plcoding.bookpedia.book.data.repository

import androidx.sqlite.SQLiteException
import com.plcoding.bookpedia.book.data.database.FavouriteBookDao
import com.plcoding.bookpedia.book.data.mappers.toBook
import com.plcoding.bookpedia.book.data.mappers.toBookEntity
import com.plcoding.bookpedia.book.data.network.RemoteBookDataSource
import com.plcoding.bookpedia.book.domaine.Book
import com.plcoding.bookpedia.book.domaine.BookRepo
import com.plcoding.bookpedia.book.domaine.DataError
import com.plcoding.bookpedia.core.domain.EmptyResult
import com.plcoding.bookpedia.core.domain.Result
import com.plcoding.bookpedia.core.domain.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookRepository(
    private val remoteBookDataSource: RemoteBookDataSource,
    private val localDataSource: FavouriteBookDao) : BookRepo {


    override suspend fun searchBook(query: String): Result<List<Book>, DataError.Remote> {

        return remoteBookDataSource.searchBooks(query).map { response ->
            response.result.map {
                it.toBook()
            }

        }
    }

    override suspend fun getBookDescription(bookId: String): Result<String?, DataError.Remote> {
        val localResult=localDataSource.getFavouriteBook(bookId)
      return  if (localResult==null){
            remoteBookDataSource.getBookDescription(bookId).map {
                it.description
            }
        }else{
            Result.Success(localResult.description)
        }

    }

    override suspend fun getFavouriteBook(): Flow<List<Book>> {
        return localDataSource.getFavouriteBooks().map {response ->
            response.map {
                it.toBook()
            }

        }
    }

    override suspend fun ifBookFavourite(id: String): Flow<Boolean> {
        return localDataSource.getFavouriteBooks().map {bookEntities ->
            bookEntities.any{
                it.id==id
            }
        }

    }

    override suspend fun markAsFavourite(book: Book): EmptyResult<DataError.Local> {
        return try {
            localDataSource.upsertBook(book.toBookEntity())
            Result.Success(Unit)
        }catch (e:SQLiteException){
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteFavourite(id: String) {
        localDataSource.deleteFavouriteBook(id)
    }


}