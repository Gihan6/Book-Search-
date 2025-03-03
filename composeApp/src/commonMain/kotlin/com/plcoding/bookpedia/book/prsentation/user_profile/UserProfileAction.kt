package com.plcoding.bookpedia.book.prsentation.user_profile

import androidx.compose.ui.graphics.ImageBitmap

sealed interface UserProfileAction {
    data class OnCameraClick(val onClick:Boolean) : UserProfileAction
    data class OnImageSelected(val imageBitmap: ImageBitmap?):UserProfileAction
    data object OnEditProfileClick : UserProfileAction
    data object OnChangePasswordClick : UserProfileAction

}