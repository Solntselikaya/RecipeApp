package com.example.recipeapp.ui.presentation.main

sealed class MainIntent {
    class OnCardClick(val id: String) : MainIntent()
    class OnSearchTextChange(val input: String) : MainIntent()
    object OnOrderIconClick : MainIntent()
    class OnOrderSelect(val order: OrderingType) : MainIntent()
    object OnDialogDismiss : MainIntent()
}
