package com.example.recipeapp.domain.usecase

import com.example.recipeapp.domain.RecipeRepository
import com.example.recipeapp.domain.model.RecipeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetRecipeListUseCase(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(): List<RecipeModel> =
        withContext(Dispatchers.IO) {
            repository.getList()
        }
}