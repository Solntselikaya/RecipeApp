package com.example.recipeapp.ui.presentation.main

sealed class MainIntent {
    class NavigateToRecipeDetails(val id: String): MainIntent()

}
