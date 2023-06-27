package com.example.recipeapp.ui.presentation.main

import com.example.recipeapp.domain.model.RecipeModel

sealed class MainState {
    object Loading : MainState()
    class Content(val recipes: List<RecipeModel>) : MainState()
    class NavigateToRecipeDetails(val id: String) : MainState()
    class Error(val message: String) : MainState()
}
