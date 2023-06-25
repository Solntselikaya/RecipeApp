package com.example.recipeapp.domain.model

data class RecipeModel(
    val description: String,
    val difficulty: String,
    val images: String,
    val instructions: String,
    val lastUpdated: String,
    val name: String,
    val uuid: String
)