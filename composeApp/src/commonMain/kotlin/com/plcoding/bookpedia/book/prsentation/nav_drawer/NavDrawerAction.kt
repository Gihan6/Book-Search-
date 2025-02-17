package com.plcoding.bookpedia.book.prsentation.nav_drawer

import com.plcoding.bookpedia.book.domaine.Book

sealed interface NavDrawerAction {
    data object OnUserClick:NavDrawerAction
    data object OnSettingClick:NavDrawerAction
    data object OnHomeClick:NavDrawerAction
    data object OnLogoutClick:NavDrawerAction
    data class OnBookClick(val book:Book):NavDrawerAction

}