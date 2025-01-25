package com.plcoding.bookpedia.book.prsentation.book_list

import com.plcoding.bookpedia.book.domaine.Book

sealed interface BookListAction {

    //here is the intent for MVI Architecture pattern that contain the all action user will do on screen
    data class OnSearchQueryChange(val query:String):BookListAction
    data class OnTabSelect(val index:Int):BookListAction
    data class OnBookClick(val book: Book):BookListAction
}