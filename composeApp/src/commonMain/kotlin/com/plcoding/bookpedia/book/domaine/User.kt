package com.plcoding.bookpedia.book.domaine

import cmp_bookpedia.composeapp.generated.resources.Res

data class User(
    val email:String,
    val name:String,
    val phone:String,
    val password:String,
    var isLogin:Boolean=false
)
