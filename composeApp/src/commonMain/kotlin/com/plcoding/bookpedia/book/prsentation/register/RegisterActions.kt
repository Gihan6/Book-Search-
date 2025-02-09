package com.plcoding.bookpedia.book.prsentation.register

import com.plcoding.bookpedia.book.domaine.User

sealed interface RegisterActions{
   data class Register( val newUSer: User):RegisterActions
   data object OnBackPress:RegisterActions
}
