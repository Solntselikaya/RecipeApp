package com.example.recipeapp.ui.navigation

import com.example.recipeapp.common.Constants.RECIPE_ID

sealed class Screen(val route: String) {
    object Main : Screen("main_screen")
    object Details : Screen("details_screen/{$RECIPE_ID}") {
        fun passRecipeId(id: String): String = "details_screen/$id"
    }
}
