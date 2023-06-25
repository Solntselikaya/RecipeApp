package com.example.recipeapp.data.dto

import com.example.recipeapp.common.parseDateForUI
import com.example.recipeapp.domain.model.RecipeModel

data class RecipeDto(
    val description: String,
    val difficulty: String,
    val images: String,
    val instructions: String,
    val lastUpdated: String,
    val name: String,
    val uuid: String
)

fun RecipeDto.toRecipeModel(): RecipeModel =
    RecipeModel(
        description = description,
        difficulty = difficulty,
        images = images,
        instructions = instructions,
        lastUpdated = parseDateForUI(lastUpdated),
        name = name,
        uuid = uuid
    )