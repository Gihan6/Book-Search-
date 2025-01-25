package com.plcoding.bookpedia.book.data.repository

import com.plcoding.bookpedia.book.data.mappers.toBook
import com.plcoding.bookpedia.book.data.network.RemoteBookDataSource
import com.plcoding.bookpedia.book.domaine.Book
import com.plcoding.bookpedia.book.domaine.BookRepo
import com.plcoding.bookpedia.book.domaine.DataError
import com.plcoding.bookpedia.core.domain.Result
import com.plcoding.bookpedia.core.domain.map

class BookRepository (private val remoteBookDataSource: RemoteBookDataSource): BookRepo {


    override  suspend fun searchBook(query:String):Result<List<Book>,DataError.Remote>{

        return remoteBookDataSource.
        searchBooks(query).map {response->
            response.result.map {
                it.toBook()
            }

        }
    }

}