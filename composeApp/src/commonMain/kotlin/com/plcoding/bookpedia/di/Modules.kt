package com.plcoding.bookpedia.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.plcoding.bookpedia.book.data.database.DataBaseFactory
import com.plcoding.bookpedia.book.data.database.FavouriteBookDatabase
import com.plcoding.bookpedia.book.data.network.KtorRemoteBookDataSource
import com.plcoding.bookpedia.book.data.network.RemoteBookDataSource
import com.plcoding.bookpedia.book.data.repository.BookRepository
import com.plcoding.bookpedia.book.domaine.BookRepo
import com.plcoding.bookpedia.book.prsentation.SelectBookViewModel
import com.plcoding.bookpedia.book.prsentation.book_detail.BookDetailViewModel
import com.plcoding.bookpedia.book.prsentation.book_list.BookListViewModel
import com.plcoding.bookpedia.core.data.HttpClientFactory
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

    single {
        get<DataBaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<FavouriteBookDatabase>().favouriteBookDao }
    viewModelOf(::BookListViewModel)
    viewModelOf(::SelectBookViewModel)
    viewModelOf(::BookDetailViewModel)




}