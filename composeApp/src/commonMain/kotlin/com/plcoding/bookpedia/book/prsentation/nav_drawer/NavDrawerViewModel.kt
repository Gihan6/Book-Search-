package com.plcoding.bookpedia.book.prsentation.nav_drawer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.bookpedia.book.domaine.UserRepo
import com.plcoding.bookpedia.core.domain.onError
import com.plcoding.bookpedia.core.domain.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NavDrawerViewModel(private val userRepo: UserRepo) : ViewModel() {
    private val _state = MutableStateFlow(NavDrawerState())
    val state = _state.onStart {

    }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    fun onAction(action: NavDrawerAction) {
        when (action) {
            is NavDrawerAction.OnLogoutClick -> {
                logOut()

            }

            else -> {}
        }
    }

    fun logOut() {
        state.value.user?.email?.let { email ->
            viewModelScope.launch {
                userRepo.logout(email).onSuccess {
                    _state.update {state->
                        state.copy(user = null, isLogout = true)
                    }

                }.onError {

                }
            }
        }
    }

}