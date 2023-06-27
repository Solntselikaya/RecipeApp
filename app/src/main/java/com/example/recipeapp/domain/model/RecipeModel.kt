package com.example.recipeapp.domain.model

data class RecipeModel(
    val description: String? = null,
    val difficulty: String,
    val images: List<String>,
    val instructions: String,
    val lastUpdated: Long,
    val name: String,
    val uuid: String
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            name,
            description ?: "",
            instructions
        )

        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}