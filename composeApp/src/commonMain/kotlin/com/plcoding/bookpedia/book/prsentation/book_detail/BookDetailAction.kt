package com.plcoding.bookpedia.book.prsentation.book_detail

import com.plcoding.bookpedia.book.domaine.Book

sealed interface BookDetailAction {

    data object OnBackClick:BookDetailAction

    data object OnFavouriteClick:BookDetailAction

    data class OnBookChange(val book:Book):BookDetailAction
}