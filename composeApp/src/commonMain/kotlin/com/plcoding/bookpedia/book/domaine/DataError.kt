package com.plcoding.bookpedia.book.domaine

import com.plcoding.bookpedia.core.domain.Error

sealed interface DataError : Error{

     enum class Remote:DataError{

         REQUEST_TIMEOUT,
         TOO_MANY_REQUESTS,
         NO_INTERNET,
         SERVER,
         SERIALIZATION,
         UNKNOWN
     }
     enum class Local:DataError{
         DISK_FULL,
         ALREADY_REGISTER,
         UNKNOWN,
         NOT_FOUND_USER,

     }
}