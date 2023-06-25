package com.example.recipeapp.data.dto

import com.example.recipeapp.common.parseDateForUI
import com.example.recipeapp.domain.model.RecipeDetailsModel

data class RecipeDetailDto(
    val recipe: RecipeDetailsDto
)

fun RecipeDetailDto.toRecipeDetailsModel(): RecipeDetailsModel =
    RecipeDetailsModel(
        description = recipe.description,
        difficulty = recipe.difficulty,
        images = recipe.images,
        instructions = recipe.description,
        lastUpdated = parseDateForUI(recipe.lastUpdated),
        name = recipe.name,
        similar = recipe.similar.map { it.toRecipeBriefModel() },
        uuid = recipe.uuid,
)