package com.plcoding.bookpedia.book.prsentation.splash_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.book_icon
import org.jetbrains.compose.resources.painterResource

@Composable
fun SplashScreen(modifier: Modifier=Modifier,navToMain:()->Unit,){

    Box(
        modifier=modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    )
    {
        Image(
            modifier=Modifier.size(68.dp),
            painter = painterResource(Res.drawable.book_icon),
            contentDescription = "logo "
        )
         navToMain()

    }
}