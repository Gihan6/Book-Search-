package com.plcoding.bookpedia.book.prsentation.login

import com.plcoding.bookpedia.book.domaine.User

sealed interface LoginActions {

    data class OnLogin(val user: User):LoginActions
    data object OnRegisterClick:LoginActions
    data object ClearError:LoginActions
}