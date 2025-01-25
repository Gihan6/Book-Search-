package com.plcoding.bookpedia.book.data.requesAndResponses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    @SerialName("docs") val result:List<SearchBookDto>
)
