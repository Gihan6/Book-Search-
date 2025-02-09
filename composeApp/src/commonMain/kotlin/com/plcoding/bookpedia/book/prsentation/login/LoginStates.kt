package com.plcoding.bookpedia.book.prsentation.login

import com.plcoding.bookpedia.core.presentation.UiText

data class LoginStates(
    val isLoading:Boolean=false,
    val isLogin:Boolean=false,
    val error: UiText?=null
)
