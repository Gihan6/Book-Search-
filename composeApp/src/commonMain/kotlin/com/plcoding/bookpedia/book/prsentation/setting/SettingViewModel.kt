package com.plcoding.bookpedia.book.prsentation.setting

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingViewModel(private val dataStore: DataStore<Preferences>) : ViewModel() {
    private val _state = MutableStateFlow(SettingStates())
    val state = _state.onStart {
        val language =
            dataStore.data.first()[stringPreferencesKey("language")] ?: Language.English.iso

        _state.update {state->
            state.copy(selectLanguage =language)

        }

    }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    fun onAction(action: SettingActions) {
        when (action) {
            is SettingActions.ChangeLanguage -> {
                viewModelScope.launch {
                    dataStore.edit { dataStore ->
                        val languageKey = stringPreferencesKey("language")
                        dataStore[languageKey] = action.language.iso
                    }
                    _state.update { state->
                        state.copy(isLaunchApp = true)
                    }
                }
            }

            else -> {

            }
        }
    }
}