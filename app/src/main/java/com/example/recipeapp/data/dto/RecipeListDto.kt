package com.example.recipeapp.data.dto

import com.example.recipeapp.domain.model.RecipeModel

data class RecipeListDto(
    val recipes: List<RecipeDto>
)

fun RecipeListDto.toRecipeListModel(): List<RecipeModel> =
    recipes.map { it.toRecipeModel() }