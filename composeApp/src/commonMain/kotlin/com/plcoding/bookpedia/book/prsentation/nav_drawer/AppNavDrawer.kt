package com.plcoding.bookpedia.book.prsentation.nav_drawer

import com.plcoding.bookpedia.app.Route
import com.plcoding.bookpedia.app.sharedKoinViewModel
import com.plcoding.bookpedia.book.prsentation.SelectBookViewModel
import com.plcoding.bookpedia.book.prsentation.book_list.BookListScreenRoot
import com.plcoding.bookpedia.book.prsentation.book_list.BookListViewModel
import com.plcoding.bookpedia.book.prsentation.setting.SettingScreen
import com.plcoding.bookpedia.core.presentation.DarkBlue
import org.koin.compose.viewmodel.koinViewModel


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.menu
import com.plcoding.bookpedia.book.domaine.Book
import com.plcoding.bookpedia.book.prsentation.user_profile.UserProfileScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    goToBookDetail:(Book)->Unit
) {
    val navigationActions = remember(navController) {
        AppNavigationActions(navController)
    }

    ModalNavigationDrawer(drawerContent = {
        AppDrawer(
            navigateToHome = {
                navigationActions.navigateToHome()
            },
            navigateToSettings = {
                navigationActions.navigateToSettings()
            },
            navigateToUserProfile = {
                navigationActions.navigateToUserProfile()
            },
            closeDrawer = { coroutineScope.launch { drawerState.close() } },
            modifier = Modifier
        )
    }, drawerState = drawerState) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {},
                    modifier = Modifier.fillMaxWidth(),
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch { drawerState.open() }
                        }, content = {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = stringResource(Res.string.menu),
                                tint = Color.White
                            )
                        })
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkBlue)
                )
            }, modifier = Modifier
        ) {
            NavHost(
                navController = navController, startDestination = Route.BookList,
                modifier = modifier.padding(top = 5.dp)
            ) {

                composable<Route.BookList> {
                    val viewModel = koinViewModel<BookListViewModel>()
                    BookListScreenRoot(
                        viewModel = viewModel,
                        onBookClick = { book ->
                            goToBookDetail(book)

                        }
                    )
                }

                composable<Route.Setting> {
                    SettingScreen()
                }
                composable<Route.UserProfile> {
                    UserProfileScreen()
                }
            }
        }
    }
}