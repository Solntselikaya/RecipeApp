package com.example.recipeapp.domain.model

data class RecipeDetailsModel(
    val description: String,
    val difficulty: String,
    val images: String,
    val instructions: String,
    val lastUpdated: String,
    val name: String,
    val similar: List<RecipeBriefModel>,
    val uuid: String
)