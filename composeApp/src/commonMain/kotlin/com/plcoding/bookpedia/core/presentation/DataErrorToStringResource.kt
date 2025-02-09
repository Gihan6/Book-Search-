package com.plcoding.bookpedia.core.presentation

import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.error_disk_full
import cmp_bookpedia.composeapp.generated.resources.error_no_internet
import cmp_bookpedia.composeapp.generated.resources.error_serializations
import cmp_bookpedia.composeapp.generated.resources.error_to_many_requests
import cmp_bookpedia.composeapp.generated.resources.error_unknown
import cmp_bookpedia.composeapp.generated.resources.user_already_exist
import cmp_bookpedia.composeapp.generated.resources.user_not_have_account
import com.plcoding.bookpedia.book.domaine.DataError


fun DataError.toUiText():UiText{

  val stringRes=  when (this){
        DataError.Local.DISK_FULL -> Res.string.error_disk_full
        DataError.Local.UNKNOWN -> Res.string.error_disk_full
        DataError.Remote.REQUEST_TIMEOUT -> Res.string.error_disk_full
        DataError.Remote.TOO_MANY_REQUESTS -> Res.string.error_to_many_requests
        DataError.Remote.NO_INTERNET -> Res.string.error_no_internet
        DataError.Remote.SERVER -> Res.string.error_unknown
        DataError.Remote.SERIALIZATION -> Res.string.error_serializations
        DataError.Remote.UNKNOWN -> Res.string.error_unknown
        DataError.Local.ALREADY_REGISTER -> Res.string.user_already_exist
      DataError.Local.NOT_FOUND_USER -> Res.string.user_not_have_account
  }
    return UiText.StringResourceId(stringRes)
}