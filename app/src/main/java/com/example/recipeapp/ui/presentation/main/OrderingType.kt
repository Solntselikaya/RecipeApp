package com.example.recipeapp.ui.presentation.main

import com.example.recipeapp.R

enum class OrderingType(val string: Int) {
    Default(R.string.no_order),
    NameAscending(R.string.by_name_ascending),
    NameDescending(R.string.by_name_descending),
    DateAscending(R.string.by_date_ascending),
    DateDescending(R.string.by_date_descending)
}