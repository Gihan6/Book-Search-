package com.plcoding.bookpedia.di

import androidx.compose.runtime.remember
import com.plcoding.bookpedia.book.data.database.DataBaseFactory
import com.plcoding.bookpedia.core.data.createDataStore
import com.plcoding.bookpedia.core.domain.Localization
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single <HttpClientEngine>{
            OkHttp.create()
        }
        single { DataBaseFactory(androidApplication()) }

        single { Localization(androidApplication()) }

        single {
            createDataStore(androidApplication())
        }

    }


