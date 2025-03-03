package com.plcoding.bookpedia.book.prsentation.user_profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.bookpedia.book.domaine.UserRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class UserProfileViewModel(userRepo: UserRepo) : ViewModel() {
    private val _state = MutableStateFlow(UserProfileState())
    val state = _state.onStart {
    }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    fun onAction(action:UserProfileAction){
        when(action){
            is UserProfileAction.OnCameraClick ->{
                _state.update {
                    it.copy(isOpenCamera = action.onClick)
                }
            }
            is UserProfileAction.OnImageSelected -> {
                if (action.imageBitmap!=null){
                    _state.update {
                        it.copy(imageBitmap = action.imageBitmap, isOpenCamera = false)
                    }
                }
            }
            UserProfileAction.OnChangePasswordClick -> TODO()
            UserProfileAction.OnEditProfileClick -> TODO()

        }
    }
}