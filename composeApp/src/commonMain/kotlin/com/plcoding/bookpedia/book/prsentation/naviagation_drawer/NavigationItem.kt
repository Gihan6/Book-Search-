package com.plcoding.bookpedia.book.prsentation.naviagation_drawer

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title:String,
    val selectIcon:ImageVector,
    val unSelectIcon:ImageVector,
    val badgeCount:Int?=null
)
