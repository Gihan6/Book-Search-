package com.plcoding.bookpedia.book.prsentation.book_detail

import com.plcoding.bookpedia.book.domaine.Book
import com.plcoding.bookpedia.book.domaine.DataError
import com.plcoding.bookpedia.core.presentation.UiText

data class BookDetailState(
    val isLoading:Boolean=true,
    val isFavourite:Boolean=false,
    val book:Book?=null,
    val error: UiText?=null,
)
