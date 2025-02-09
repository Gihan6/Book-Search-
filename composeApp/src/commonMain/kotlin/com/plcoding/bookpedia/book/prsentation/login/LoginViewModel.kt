package com.plcoding.bookpedia.book.prsentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.please_enter_complete_data
import cmp_bookpedia.composeapp.generated.resources.user_not_have_account
import com.plcoding.bookpedia.book.data.repository.UserRepository
import com.plcoding.bookpedia.book.domaine.User
import com.plcoding.bookpedia.core.domain.onError
import com.plcoding.bookpedia.core.domain.onSuccess
import com.plcoding.bookpedia.core.presentation.UiText
import com.plcoding.bookpedia.core.presentation.toUiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository) : ViewModel() {

    private val _state = MutableStateFlow(LoginStates())
    val state = _state.onStart {

    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000L), _state.value
    )

    fun onAction(actions: LoginActions) {
        when (actions) {
            is LoginActions.ClearError ->{
                _state.update {
                    it.copy(error = null)
                }
            }
            is LoginActions.OnLogin -> {
                _state.update {
                    it.copy(isLoading = true, error = null)
                }
                if (validateLoginDate(actions.user.email, actions.user.password)) {

                    login(actions.user.email, actions.user.password)

                } else {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = UiText.StringResourceId(Res.string.please_enter_complete_data)
                        )
                    }
                }

            }

            else -> null
        }
    }

    private fun login(email: String, password: String) {
        viewModelScope.launch {
            repository.login(email, password).onSuccess {
                _state.update {
                    it.copy(
                        isLoading = false, isLogin = true, error = null
                    )
                }
            }.onError {error ->
                    _state.update {
                        it.copy(
                            isLoading = false, error = UiText.StringResourceId(Res.string.user_not_have_account)
                        )
                    }
                }
        }


    }

    private fun validateLoginDate(email: String, password: String): Boolean {
       return if (email.isEmpty())
             false
        else if (password.isEmpty())
             false
        else
             true
    }
}