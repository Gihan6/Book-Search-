package com.plcoding.bookpedia.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.plcoding.bookpedia.book.data.database.DataBaseFactory
import com.plcoding.bookpedia.book.data.database.FavouriteBookDatabase
import com.plcoding.bookpedia.book.data.network.KtorRemoteBookDataSource
import com.plcoding.bookpedia.book.data.network.RemoteBookDataSource
import com.plcoding.bookpedia.book.data.repository.BookRepository
import com.plcoding.bookpedia.book.data.repository.UserRepository
import com.plcoding.bookpedia.book.domaine.BookRepo
import com.plcoding.bookpedia.book.domaine.UserRepo
import com.plcoding.bookpedia.book.prsentation.SelectBookViewModel
import com.plcoding.bookpedia.book.prsentation.book_detail.BookDetailViewModel
import com.plcoding.bookpedia.book.prsentation.book_list.BookListViewModel
import com.plcoding.bookpedia.book.prsentation.login.LoginViewModel
import com.plcoding.bookpedia.book.prsentation.nav_drawer.NavDrawerViewModel
import com.plcoding.bookpedia.book.prsentation.register.RegisterViewModel
import com.plcoding.bookpedia.book.prsentation.setting.SettingViewModel
import com.plcoding.bookpedia.book.prsentation.splash_screen.SplashViewModel
import com.plcoding.bookpedia.book.prsentation.user_profile.UserProfileViewModel
import com.plcoding.bookpedia.core.data.HttpClientFactory
import com.plcoding.bookpedia.core.data.createDataStore
import com.plcoding.bookpedia.core.domain.Localization
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module


expect val platformModule: Module

val shareModule= module {
    single {
        HttpClientFactory.create(get())
    }
    singleOf(::KtorRemoteBookDataSource).bind<RemoteBookDataSource>()
    singleOf(::BookRepository).bind<BookRepo>()
    singleOf(::UserRepository).bind<UserRepo>()

    single {
        get<DataBaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single {
        get<Localization>()
    }
    single { get<FavouriteBookDatabase>().favouriteBookDao }
    single { get<FavouriteBookDatabase>().userDao }

    single {
       get<DataStore<Preferences>>()

    }
    viewModelOf(::BookListViewModel)
    viewModelOf(::SelectBookViewModel)
    viewModelOf(::BookDetailViewModel)
    viewModelOf(::SplashViewModel)
    viewModelOf(::RegisterViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::NavDrawerViewModel)
    viewModelOf(::UserProfileViewModel)
    viewModelOf(::SettingViewModel)




}