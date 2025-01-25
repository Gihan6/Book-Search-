package com.plcoding.bookpedia.book.prsentation.book_list

import com.plcoding.bookpedia.book.domaine.Book
import com.plcoding.bookpedia.book.domaine.books
import com.plcoding.bookpedia.core.presentation.UiText

data class BookListState(
    val searchQuery:String="kotlin",
    val searchResult:List<Book> = books,
    val favouriteResult:List<Book> = emptyList(),
    val isLoading:Boolean=false,
    val selectIndexTap :Int =0,
    val error:UiText?=null

)
