package com.plcoding.bookpedia.book.data.network

import com.plcoding.bookpedia.book.data.requesAndResponses.BookDescriptionDto
import com.plcoding.bookpedia.book.data.requesAndResponses.SearchBookDto
import com.plcoding.bookpedia.book.data.requesAndResponses.SearchResponse
import com.plcoding.bookpedia.book.domaine.Book
import com.plcoding.bookpedia.book.domaine.DataError
import com.plcoding.bookpedia.core.data.saveCall
import com.plcoding.bookpedia.core.domain.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class KtorRemoteBookDataSource(private val httpClient: HttpClient):RemoteBookDataSource {

    private val baseUrl="https://openLibrary.org"

    override suspend fun searchBooks(
        query:String,
        queryLimit:Int?
    )
    :Result<SearchResponse,DataError.Remote>
    {

        return saveCall<SearchResponse> {
            httpClient.get(urlString = "$baseUrl/search.json"){
                parameter("q",query)
                parameter("limit",query)
                parameter("language","eng")
                parameter("fields", "key,title,author_name,author_key,cover_edition_key,cover_i,ratings_average,ratings_count,first_publish_year,language,number_of_pages_median,edition_count")
            }
        }
    }

    override suspend fun getBookDescription(bookId: String): Result<BookDescriptionDto, DataError.Remote> {
        return saveCall<BookDescriptionDto> {
            httpClient.get(urlString = "$baseUrl/works/$bookId.json")
        }
    }


}