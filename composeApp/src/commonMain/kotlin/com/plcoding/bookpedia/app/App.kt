package com.plcoding.bookpedia.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.plcoding.bookpedia.book.prsentation.SelectBookViewModel
import com.plcoding.bookpedia.book.prsentation.book_detail.BookDetailAction
import com.plcoding.bookpedia.book.prsentation.book_detail.BookDetailScreenRoot
import com.plcoding.bookpedia.book.prsentation.book_detail.BookDetailViewModel
import com.plcoding.bookpedia.book.prsentation.book_list.BookListScreenRoot
import com.plcoding.bookpedia.book.prsentation.book_list.BookListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {

    MaterialTheme {
        val navController= rememberNavController()
        NavHost(
          navController=  navController,
            startDestination = Route.BookGraph
        ){
            navigation<Route.BookGraph>(startDestination = Route.BookList){
                composable<Route.BookList> {
                    val viewModel=koinViewModel<BookListViewModel>()
                    val selectedBookViewModel=
                        it.sharedKoinViewModel<SelectBookViewModel>(navController)
                    //Appear when enter screen for first time or back
                    LaunchedEffect(true){
                        selectedBookViewModel.onSelectedBook(null)
                    }
                    BookListScreenRoot(
                        viewModel = viewModel,
                        onBookClick = {book ->
                            selectedBookViewModel.onSelectedBook(book)
                            navController.navigate(Route.BookDetail(book.id))

                        }

                    )
                }
                composable<Route.BookDetail> {
                    val selectedBookViewModel=
                        it.sharedKoinViewModel<SelectBookViewModel>(navController)
                    val selectedBook by selectedBookViewModel.selectedBook.collectAsStateWithLifecycle()
                    val viewModel=koinViewModel<BookDetailViewModel>()

                    LaunchedEffect(selectedBook){
                        selectedBook?.let {book ->
                            viewModel.onAction(BookDetailAction.OnBookChange(book))

                        }
                    }

                    BookDetailScreenRoot(
                        viewModel=viewModel,
                        onBackClick = {
                            navController.navigateUp()
                        }

                    )

                }
            }
        }

    }

}
@Composable
private inline fun <reified T:ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
):T{
    val navGraphRoute=destination.parent?.route?: return koinViewModel<T>()
    val parentEntry= remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )
}