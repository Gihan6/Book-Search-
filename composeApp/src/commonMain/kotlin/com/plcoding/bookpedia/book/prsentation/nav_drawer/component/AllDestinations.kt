package com.plcoding.bookpedia.book.prsentation.nav_drawer.component

import androidx.navigation.NavHostController
import com.plcoding.bookpedia.app.Route




class AppNavigationActions(private val navController: NavHostController) {
    fun navigateToHome() {
        navController.navigate(Route.BookList) {
            popUpTo(Route.BookList)
        }
    }

    fun navigateToSettings() {
        navController.navigate(Route.Setting) {
            launchSingleTop = true
            restoreState = true
        }
    }
    fun navigateToUserProfile() {
        navController.navigate(Route.UserProfile) {
            launchSingleTop = true
            restoreState = true
        }
    }
}
