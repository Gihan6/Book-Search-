package com.plcoding.bookpedia.book.data.requesAndResponses

import kotlinx.serialization.Serializable

@Serializable(with = BookDescriptionDtoSerializer::class)
data class BookDescriptionDto(
    val description:String?=null
)
