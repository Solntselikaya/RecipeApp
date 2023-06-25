package com.example.recipeapp.common

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun parseDateForUI(date: String): String {
    return date.toLong().toDateStringWithDots()
}

fun Long.toDateStringWithDots() : String {
    val dateTime = Date(this * 1000L)
    val format = SimpleDateFormat("dd.MM.yyyy", Locale.US)
    return format.format(dateTime)
}