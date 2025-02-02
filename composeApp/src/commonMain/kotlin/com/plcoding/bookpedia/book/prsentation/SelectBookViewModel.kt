package com.plcoding.bookpedia.book.prsentation

import androidx.lifecycle.ViewModel
import com.plcoding.bookpedia.book.domaine.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SelectBookViewModel:ViewModel() {
    private val _selectedBook= MutableStateFlow<Book?>(null)
    val selectedBook=_selectedBook.asStateFlow()

    fun onSelectedBook(book:Book?){
        _selectedBook.value=book

    }

}