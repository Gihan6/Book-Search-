package com.plcoding.bookpedia.book.prsentation.splash_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.bookpedia.app.Utils.Companion.loginUser
import com.plcoding.bookpedia.book.domaine.UserRepo
import com.plcoding.bookpedia.core.domain.onError
import com.plcoding.bookpedia.core.domain.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashViewModel(val repo: UserRepo) : ViewModel() {

    private val _state = MutableStateFlow(SplashScreenStates())
    val state = _state.onStart {
        ifUserLogin()
    }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    fun ifUserLogin() {
        viewModelScope.launch {
            repo.getLoginUser().onSuccess { user ->
                loginUser=user
                _state.update {
                    it.copy(isLogin = if (user == null) false else true)
                }
            }.onError {
                _state.update {
                    it.copy(isLogin = false)
                }
            }


        }
    }
}