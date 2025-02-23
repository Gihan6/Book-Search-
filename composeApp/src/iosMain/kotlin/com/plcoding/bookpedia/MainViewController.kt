package com.plcoding.bookpedia

import androidx.compose.ui.window.ComposeUIViewController
import com.plcoding.bookpedia.di.initKoin
import com.plcoding.bookpedia.app.App



fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}