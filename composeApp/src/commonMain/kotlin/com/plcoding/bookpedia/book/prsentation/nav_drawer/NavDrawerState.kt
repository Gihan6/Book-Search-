package com.plcoding.bookpedia.book.prsentation.nav_drawer

import com.plcoding.bookpedia.app.Utils.Companion.loginUser
import com.plcoding.bookpedia.book.domaine.Book
import com.plcoding.bookpedia.book.domaine.User

data class NavDrawerState(
    val user: User? = loginUser,
    val isLogout:Boolean=false
    )
