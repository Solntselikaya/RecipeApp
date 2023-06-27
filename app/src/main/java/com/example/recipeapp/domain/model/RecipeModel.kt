package com.example.recipeapp.domain.model

data class RecipeModel(
    val description: String? = null,
    val difficulty: String,
    val images: List<String>,
    val instructions: String,
    val lastUpdated: String,
    val name: String,
    val uuid: String
)