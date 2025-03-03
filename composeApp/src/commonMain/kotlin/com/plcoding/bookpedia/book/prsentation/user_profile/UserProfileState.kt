package com.plcoding.bookpedia.book.prsentation.user_profile

import androidx.compose.ui.graphics.ImageBitmap
import com.plcoding.bookpedia.app.Utils.Companion.loginUser
import com.plcoding.bookpedia.book.domaine.User

data class UserProfileState(
    val user:User?= loginUser,
    val isOpenCamera:Boolean=false,
    val imageBitmap: ImageBitmap?=null
)
