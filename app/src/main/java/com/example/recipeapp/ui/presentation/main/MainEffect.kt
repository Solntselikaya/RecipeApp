package com.example.recipeapp.ui.presentation.main

sealed class MainEffect {
    class NavigateToDetails(val id: String) : MainEffect()
}
