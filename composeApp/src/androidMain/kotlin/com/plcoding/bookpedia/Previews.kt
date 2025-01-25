package com.plcoding.bookpedia

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.plcoding.bookpedia.book.domaine.Book
import com.plcoding.bookpedia.book.domaine.books
import com.plcoding.bookpedia.book.prsentation.book_list.BookListScreen
import com.plcoding.bookpedia.book.prsentation.book_list.BookListState
import com.plcoding.bookpedia.book.prsentation.book_list.components.BookSearchBar

@Preview(
    name = "Search bar"
)
@Composable
fun BookSearchBarPreview() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        BookSearchBar(
            searchQuery = "kotlin",
            onSearchQueryChange = {},
            onImeSearch = {},
            Modifier.fillMaxWidth()
        )
    }
}


@Preview()
@Composable
fun BookListScreenPreview() {

    BookListScreen(
        state= BookListState(
            searchResult = books
            ),
        onAction = {

        }
    )
}
