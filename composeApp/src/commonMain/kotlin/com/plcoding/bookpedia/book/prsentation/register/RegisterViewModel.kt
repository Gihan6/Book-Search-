package com.plcoding.bookpedia.book.prsentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.please_enter_complete_data
import com.plcoding.bookpedia.app.Utils.Companion.loginUser
import com.plcoding.bookpedia.book.domaine.User
import com.plcoding.bookpedia.book.domaine.UserRepo
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

class RegisterViewModel(private val repository: UserRepo) : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.onStart {
    }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )


    fun onAction(action: RegisterActions) {

        when (action) {
            is RegisterActions.Register -> {
                _state.update {
                    it.copy(isLoading = true, error = null)
                }

              if (  validateUser(action.newUSer)){
                  _state.update {
                      it.copy(isLoading = true)
                  }
                  register(action.newUSer)
              }else{
                  _state.update {
                      it.copy(isLoading = true, error = UiText.StringResourceId(Res.string.please_enter_complete_data))
                  }
              }
            }
            else -> null
        }
    }
    private fun register(newUser: User){
        viewModelScope.launch {
            repository.register(newUser).onSuccess {
                loginUser =newUser
                _state.update {
                    it.copy(
                        isLoading = false,
                        registerSuccess = true
                    )


                }
            }.onError {error ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = error.toUiText()
                    )
                }
            }
        }
    }


    private fun validateUser(newUser: User): Boolean {
        return if (newUser.email.isEmpty())
            false
        else if (newUser.name.isEmpty())
            false
        else if (newUser.password.isEmpty())
            false
        else if (newUser.phone.isEmpty())
            false
        else
            true
    }
}