package com.plcoding.bookpedia.book.prsentation.book_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.bookpedia.book.domaine.Book
import com.plcoding.bookpedia.book.domaine.BookRepo
import com.plcoding.bookpedia.core.domain.onError
import com.plcoding.bookpedia.core.domain.onSuccess
import com.plcoding.bookpedia.core.presentation.toUiText
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookListViewModel(private val repository: BookRepo) : ViewModel() {
    private val _state = MutableStateFlow(BookListState())
    val state = _state
        .onStart {
            if (cashBookList.isEmpty()){
                observeSearchQuery()
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )
    private var searchJop:Job?=null

    private var cashBookList: List<Book> = emptyList()
    fun onAction(action: BookListAction) {
        when (action) {
            is BookListAction.OnBookClick -> {

            }

            is BookListAction.OnSearchQueryChange -> {
                _state.update {
                    it.copy(searchQuery = action.query)
                }
            }

            is BookListAction.OnTabSelect -> {
                _state.update {
                    it.copy(selectIndexTap = action.index)
                }
            }
        }

    }

    private fun observeSearchQuery() {
        state.map { it.searchQuery }
            .distinctUntilChanged()
            .debounce(500L)
            .onEach { query ->
                when {
                    query.isBlank() -> {
                        _state.update { it.copy(error = null, searchResult = cashBookList) }
                    }

                    query.length >= 2 -> {
                        searchJop?.cancel()
                        searchJop=searchBook(query)
                    }

                    else -> {}
                }

            }
            .launchIn(viewModelScope)
    }

    private fun searchBook(query: String) = viewModelScope.launch {

        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            repository.searchBook(query)
                .onSuccess { books ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = null,
                            searchResult = books
                        )
                    }

                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = error.toUiText()
                        )
                    }

                }
        }
    }

}