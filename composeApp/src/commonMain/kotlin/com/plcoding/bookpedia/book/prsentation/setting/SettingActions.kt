package com.plcoding.bookpedia.book.prsentation.setting

interface SettingActions {
    data class ChangeLanguage(val language: Language):SettingActions
}