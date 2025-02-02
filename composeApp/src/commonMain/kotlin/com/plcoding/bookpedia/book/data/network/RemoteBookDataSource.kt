package com.plcoding.bookpedia.book.data.network

import com.plcoding.bookpedia.book.data.requesAndResponses.BookDescriptionDto
import com.plcoding.bookpedia.book.data.requesAndResponses.SearchResponse
import com.plcoding.bookpedia.book.domaine.DataError
import com.plcoding.bookpedia.core.domain.Result

interface RemoteBookDataSource {

    suspend fun searchBooks(
        query:String,
        queryLimit:Int?=null
    ):Result<SearchResponse,DataError.Remote>

    suspend fun getBookDescription(
        bookId:String
    ):Result<BookDescriptionDto,DataError.Remote>
}