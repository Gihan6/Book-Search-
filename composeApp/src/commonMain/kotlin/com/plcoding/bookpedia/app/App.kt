package com.plcoding.bookpedia.app

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.plcoding.bookpedia.book.prsentation.SelectBookViewModel
import com.plcoding.bookpedia.book.prsentation.book_detail.BookDetailAction
import com.plcoding.bookpedia.book.prsentation.book_detail.BookDetailScreenRoot
import com.plcoding.bookpedia.book.prsentation.book_detail.BookDetailViewModel
import com.plcoding.bookpedia.book.prsentation.login.LoginScreenRoot
import com.plcoding.bookpedia.book.prsentation.login.LoginViewModel
import com.plcoding.bookpedia.book.prsentation.nav_drawer.AppNavGraph
import com.plcoding.bookpedia.book.prsentation.register.RegisterScreenRoot
import com.plcoding.bookpedia.book.prsentation.register.RegisterViewModel
import com.plcoding.bookpedia.book.prsentation.splash_screen.SplashScreenRoot
import com.plcoding.bookpedia.book.prsentation.splash_screen.SplashViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {

    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController, startDestination = Route.BookGraph
        ) {
            navigation<Route.BookGraph>(startDestination = Route.SplashScreen) {

                composable<Route.SplashScreen>(enterTransition = {
                    slideInVertically { initialOfSet ->
                        initialOfSet
                    }
                }, exitTransition = {
                    slideOutVertically { initialOfSet ->
                        initialOfSet
                    }
                }) {
                    val viewModel = koinViewModel<SplashViewModel>()
                    SplashScreenRoot(viewModel = viewModel, navToMain = {
                        navController.navigate(Route.Navigation)
                    }, navToLogin = {
                        navController.navigate(Route.Login)
                    })

                }

                composable<Route.Navigation> {
                    val selectedBookViewModel =
                        it.sharedKoinViewModel<SelectBookViewModel>(navController)
                    AppNavGraph(goToBookDetail = { book ->
                        selectedBookViewModel.onSelectedBook(book)
                        navController.navigate(Route.BookDetail(book.id))
                    })
                }
                composable<Route.BookDetail>(enterTransition = {
                    slideInHorizontally { initialOfSet ->
                        initialOfSet
                    }
                }, exitTransition = {
                    slideOutHorizontally { initialOfSet ->
                        initialOfSet
                    }
                }) {
                    val selectedBookViewModel =
                        it.sharedKoinViewModel<SelectBookViewModel>(navController)
                    val selectedBook by selectedBookViewModel.selectedBook.collectAsStateWithLifecycle()
                    val viewModel = koinViewModel<BookDetailViewModel>()

                    LaunchedEffect(selectedBook) {
                        selectedBook?.let { book ->
                            viewModel.onAction(BookDetailAction.OnBookChange(book))
                        }
                    }
                    BookDetailScreenRoot(viewModel = viewModel, onBackClick = {
                        navController.navigateUp()
                    })
                }
                composable<Route.Login>(enterTransition = {
                    slideInHorizontally { initialOfSet ->
                        initialOfSet
                    }
                }, exitTransition = {
                    slideOutHorizontally { initialOfSet ->
                        initialOfSet
                    }
                }) {

                    val viewModel = koinViewModel<LoginViewModel>()
                    LoginScreenRoot(viewModel = viewModel, navigateToRegister = {
                        navController.navigate(Route.Register)
                    }, navigateToMain = {
                        navController.navigate(Route.BookList)

                    })
                }
                composable<Route.Register>(enterTransition = {
                    slideInHorizontally { initialOfSet ->
                        initialOfSet
                    }
                }, exitTransition = {
                    slideOutHorizontally { initialOfSet ->
                        initialOfSet
                    }
                }) {
                    val viewModel = koinViewModel<RegisterViewModel>()


                    RegisterScreenRoot(viewModel = viewModel, onBackClick = {
                        navController.navigateUp()
                    }, navigateToMain = {
                        navController.navigate(Route.BookList)
                    })
                }

            }
        }

    }

}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )
}