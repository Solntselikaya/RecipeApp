package com.example.recipeapp.data.dto

data class RecipeDetailsDto(
    val description: String,
    val difficulty: String,
    val images: String,
    val instructions: String,
    val lastUpdated: String,
    val name: String,
    val similar: List<RecipeBriefDto>,
    val uuid: String
)