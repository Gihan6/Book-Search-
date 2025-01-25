package com.plcoding.bookpedia.book.data.mappers

import com.plcoding.bookpedia.book.data.requesAndResponses.SearchBookDto
import com.plcoding.bookpedia.book.domaine.Book


fun SearchBookDto.toBook():Book{
    return Book(
        id=id,
        title=title,
        imageUrl = if (coverKey!=null) {
            "https://covers.openlibrary.org/b/olid/${coverKey}-L.jpg"
        } else {
            "https://covers.openlibrary.org/b/id/${coverAlternativeKey}-L.jpg"
        },
        authors=authorNames?: emptyList(),
        description = null,
        language = languages?: emptyList(),
        firstPublishYear = publishYear.toString(),
        averageRating = ratingAverage,
        ratingCount=ratingCount,
        numEdition=numEdition?:0,
        numPages = numberOfPages



    )
}