package com.plcoding.bookpedia.book.prsentation.book_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
fun TitleContent(
    modifier: Modifier=Modifier,
    title:String,
    content:@Composable () ->Unit
){
    Column(
        modifier=modifier,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            modifier = modifier.align(
                alignment = Alignment.CenterHorizontally
            )
        )
        content()
    }

}