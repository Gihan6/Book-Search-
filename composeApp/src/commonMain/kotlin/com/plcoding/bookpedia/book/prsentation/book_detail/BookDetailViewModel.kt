package com.plcoding.bookpedia.book.prsentation.book_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.plcoding.bookpedia.app.Route
import com.plcoding.bookpedia.book.domaine.BookRepo
import com.plcoding.bookpedia.core.domain.onError
import com.plcoding.bookpedia.core.domain.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookDetailViewModel(
    private val bookRepo: BookRepo,
    private val saveStateHandle:SavedStateHandle) : ViewModel() {
    private val bookId=saveStateHandle.toRoute<Route.BookDetail>().id

    private val _state = MutableStateFlow(BookDetailState())
    val state = _state
        .onStart {
            fetchBookDescription()
            observeFavouriteStatus()
        }.stateIn(
           scope =  viewModelScope,
           started =  SharingStarted.WhileSubscribed(5000L),
           initialValue =  _state.value
        )


    fun onAction(action: BookDetailAction) {
        when (action) {
            BookDetailAction.OnBackClick -> {}
            is BookDetailAction.OnBookChange -> {
                _state.update {
                    it.copy(book = action.book)
                }


            }

            BookDetailAction.OnFavouriteClick -> {
                viewModelScope.launch {
                    if(_state.value.isFavourite) {
                        bookRepo.deleteFavourite(bookId)
                    }else{
                        _state.value.book?.let {book ->
                            bookRepo.markAsFavourite(book)

                        }
                    }
                    observeFavouriteStatus()

                }


            }
        }
    }

    private suspend fun observeFavouriteStatus(){

            bookRepo.ifBookFavourite(bookId)
                .onEach {favourite ->
                    _state.update {
                        it.copy(isFavourite = favourite)
                    }

                }.launchIn(viewModelScope)

    }
    private fun fetchBookDescription() = viewModelScope.launch {


        bookRepo.getBookDescription(bookId)
            .onSuccess {desc ->
                _state.update {
                    it.copy(
                        book = it.book?.copy(
                            description = desc
                        ),
                        isLoading = false)
                }


            }.onError {

            }
    }

}