package com.plcoding.bookpedia

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.plcoding.bookpedia.di.initKoin
import com.plcoding.bookpedia.app.App
import com.plcoding.bookpedia.core.data.createDataStore

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App(

    )
}