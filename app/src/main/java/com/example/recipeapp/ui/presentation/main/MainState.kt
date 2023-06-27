package com.example.recipeapp.ui.presentation.main

import com.example.recipeapp.domain.model.RecipeModel

sealed class MainState {
    object Loading : MainState()
    data class Content(
        val recipes: List<RecipeModel>,
        val search: String = "",
        val searchResult: List<RecipeModel> = emptyList(),
        val order: OrderingType = OrderingType.Default,
        val isSelectingOrder: Boolean = false
    ) : MainState()
    class Failure(val message: String) : MainState()
}
