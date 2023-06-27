package com.example.recipeapp.ui.presentation.main

sealed class MainIntent {
    class OnCardClick(val id: String) : MainIntent()
}
