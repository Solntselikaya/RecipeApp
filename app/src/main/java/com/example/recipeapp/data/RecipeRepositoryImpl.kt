package com.example.recipeapp.data

import com.example.recipeapp.data.dto.toRecipeDetailsModel
import com.example.recipeapp.data.dto.toRecipeListModel
import com.example.recipeapp.domain.RecipeRepository
import com.example.recipeapp.domain.model.RecipeDetailsModel
import com.example.recipeapp.domain.model.RecipeModel

class RecipeRepositoryImpl(
    private val api: RecipeApi
) : RecipeRepository {

    override suspend fun getList(): List<RecipeModel> {
        return api.getList().toRecipeListModel()
    }

    override suspend fun getDetailsById(id: String): RecipeDetailsModel {
        return api.getDetailsById(id).toRecipeDetailsModel()
    }

}