package com.example.recipeapp.domain.usecase

import com.example.recipeapp.domain.RecipeRepository
import com.example.recipeapp.domain.model.RecipeDetailsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetRecipeDetailsByIdUseCase(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(id: String): RecipeDetailsModel =
        withContext(Dispatchers.IO) {
            repository.getDetailsById(id)
        }
}