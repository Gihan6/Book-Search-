package com.plcoding.bookpedia.book.prsentation.book_list

import com.plcoding.bookpedia.book.domaine.Book
import com.plcoding.bookpedia.core.presentation.UiText

data class BookListState(
    val searchQuery:String="kotlin",
    val searchResult:List<Book> = emptyList(),
    val favouriteResult:List<Book> = emptyList(),
    val isLoading:Boolean=true,
    val selectIndexTap :Int =0,
    val error:UiText?=null

)
