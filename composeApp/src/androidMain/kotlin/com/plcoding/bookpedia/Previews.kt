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
import com.plcoding.bookpedia.book.prsentation.book_list.BookListScreen
import com.plcoding.bookpedia.book.prsentation.book_list.BookListState
import com.plcoding.bookpedia.book.prsentation.book_list.components.BookSearchBar
import com.plcoding.bookpedia.book.prsentation.login.LoginScreen
import com.plcoding.bookpedia.book.prsentation.login.LoginStates
import com.plcoding.bookpedia.book.prsentation.naviagation_drawer.NavigationScreen
import com.plcoding.bookpedia.book.prsentation.register.RegisterScreen

@Preview
@Composable
fun NavigationDrawer(){
    NavigationScreen()
}
@Preview
@Composable
fun Register() {
    //   RegisterScreen()
}

@Preview
@Composable
fun Login() {
    LoginScreen(
        modifier = Modifier,
        state = LoginStates(
            isLogin = false,
            isLoading = false,
            error = null
        ),
        onAction = {

        })
}

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
        state = BookListState(
            searchResult = books
        ),
        onAction = {

        }
    )
}

private val books = (1..100).map {
    Book(
        id = "$it",
        title = "book name $it",
        imageUrl = "https://test.com",
        authors = listOf("Gian"),
        description = "description $it",
        language = emptyList(),
        firstPublishYear = null,
        averageRating = 2.3676,
        ratingCount = 5,
        numPages = 6,
        numEdition = 3
    )
}
