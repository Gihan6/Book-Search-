package com.plcoding.bookpedia.book.domaine

import com.plcoding.bookpedia.core.domain.Result

interface BookRepo {

   suspend fun searchBook(query:String):Result<List<Book>,DataError.Remote>
}