package com.plcoding.bookpedia.book.domaine

data class Book(
    val id:String,
    val title:String,
    val imageUrl:String,
    val authors:List<String>,
    var description:String?,
    val language:List<String>?,
    val firstPublishYear:String?,
    val averageRating:Double?,
    val ratingCount:Int?,
    val numPages:Int?,
    val numEdition:Int
)


