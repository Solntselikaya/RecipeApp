package com.example.recipeapp.data.dto

import com.example.recipeapp.domain.model.RecipeBriefModel

data class RecipeBriefDto(
    val name: String,
    val uuid: String
)

fun RecipeBriefDto.toRecipeBriefModel(): RecipeBriefModel =
    RecipeBriefModel(
        name = name,
        uuid = uuid
    )