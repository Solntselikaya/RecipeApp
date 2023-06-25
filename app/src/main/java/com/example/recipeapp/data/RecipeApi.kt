package com.example.recipeapp.data

import com.example.recipeapp.data.dto.RecipeDetailDto
import com.example.recipeapp.data.dto.RecipeListDto
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeApi {

    @GET("recipes")
    suspend fun getList(): RecipeListDto

    @GET("recipes/{uuid}")
    suspend fun getDetailsById(@Path("uuid") id: String): RecipeDetailDto

}