package com.plcoding.bookpedia.book.data.network

import com.plcoding.bookpedia.book.data.requesAndResponses.SearchResponse
import com.plcoding.bookpedia.book.domaine.Book
import com.plcoding.bookpedia.book.domaine.DataError
import com.plcoding.bookpedia.core.data.saveCall
import com.plcoding.bookpedia.core.domain.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.parameters

class KtorRemoteBookDataSource(private val httpClient: HttpClient):RemoteBookDataSource {

    private val baseUrl="https://openLibrary.org"

    override suspend fun searchBooks(
        query:String,
        queryLimit:Int?
    )
    :Result<SearchResponse,DataError.Remote>
    {

        return saveCall {
            httpClient.get(urlString = "$baseUrl/search.json"){
                parameter("q",query)
                parameter("limit",query)
                parameter("language","eng")
                parameter("filed","key,title,language,cover_i,author_key,author_name,cover_edition_key,first_publish_year,rating_average,rating_count,number_of_pages_median,edition_count")
            }
        }
    }


}