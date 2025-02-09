package com.plcoding.bookpedia.book.data.mappers

import com.plcoding.bookpedia.book.data.database.BookEntity
import com.plcoding.bookpedia.book.data.database.UserEntity
import com.plcoding.bookpedia.book.data.requesAndResponses.SearchBookDto
import com.plcoding.bookpedia.book.domaine.Book
import com.plcoding.bookpedia.book.domaine.User


fun SearchBookDto.toBook(): Book {
    return Book(
        id = id.substringAfterLast("/"),
        title = title,
        imageUrl = if (coverKey != null) {
            "https://covers.openlibrary.org/b/olid/${coverKey}-L.jpg"
        } else {
            "https://covers.openlibrary.org/b/id/${coverAlternativeKey}-L.jpg"
        },
        authors = authorNames ?: emptyList(),
        description = null,
        language = languages ?: emptyList(),
        firstPublishYear = firstPublishYear.toString(),
        averageRating = ratingsAverage,
        ratingCount = ratingsCount,
        numEdition = numEditions ?: 0,
        numPages = numPagesMedian


    )
}

fun Book.toBookEntity(): BookEntity {
    return BookEntity(
        id = id,
        title = title,
        imageUrl = imageUrl,
        authors = authors,
        description = description,
        language = language,
        firstPublishYear = firstPublishYear,
        averageRating = averageRating,
        ratingCount = ratingCount,
        numEdition = numEdition,
        numPages = null
    )

}

fun BookEntity.toBook(): Book {
    return Book(
        id = id,
        title = title,
        imageUrl = imageUrl,
        authors = authors,
        description = description,
        language = language,
        firstPublishYear = firstPublishYear,
        averageRating = averageRating,
        ratingCount = ratingCount,
        numEdition = numEdition,
        numPages = null
    )
}

fun UserEntity.toUser(): User {
    return User(
        email = email,
        name = phone,
        phone = phone,
        password = password,
        isLogin=isLogin
    )
}
fun User.toUser(): UserEntity {
    return UserEntity(
        email = email,
        name = phone,
        phone = phone,
        password = password,
        isLogin=isLogin
    )
}