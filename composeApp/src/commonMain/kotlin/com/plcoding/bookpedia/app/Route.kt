package com.plcoding.bookpedia.app

import kotlinx.serialization.Serializable

interface Route {

    @Serializable
    data object BookGraph: Route
    @Serializable
    data object Navigation: Route

    @Serializable
    data object SplashScreen:Route

    @Serializable
    data object BookList: Route

    @Serializable
    data class BookDetail(val id:String): Route

    @Serializable
    data object Login :Route

    @Serializable
    data object Register :Route

    @Serializable
    data object Setting:Route

    @Serializable
    data object UserProfile:Route
}