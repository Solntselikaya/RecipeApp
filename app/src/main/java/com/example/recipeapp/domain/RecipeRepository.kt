package com.example.recipeapp.domain

import com.example.recipeapp.domain.model.RecipeDetailsModel
import com.example.recipeapp.domain.model.RecipeModel

interface RecipeRepository {

    suspend fun getList(): List<RecipeModel>

    suspend fun getDetailsById(id: String): RecipeDetailsModel

}