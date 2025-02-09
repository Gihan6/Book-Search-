package com.plcoding.bookpedia.book.prsentation.register


import com.plcoding.bookpedia.core.presentation.UiText

data class RegisterState(
    val isLoading:Boolean =false,
    val registerSuccess:Boolean=false,
    val error:UiText?=null

)
