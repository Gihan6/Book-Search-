package com.plcoding.bookpedia.book.domaine

data class Book(
    val id:String,
    val title:String,
    val imageUrl:String,
    val authors:List<String>,
    val description:String?,
    val language:List<String>?,
    val firstPublishYear:String?,
    val averageRating:Double?,
    val ratingCount:Int?,
    val numPages:Int?,
    val numEdition:Int
)

 val books = (1..100).map {
    Book(
        id = "$it",
        title = "book name $it",
        imageUrl = "https://test.com",
        authors = listOf("Gian"),
        description = "description $it",
        language = emptyList(),
        firstPublishYear = null,
        averageRating = 2.3676,
        ratingCount = 5,
        numPages = 6,
        numEdition = 3
    )
}