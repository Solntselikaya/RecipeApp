package com.example.recipeapp.ui.presentation.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.domain.usecase.GetRecipeListUseCase
import com.example.recipeapp.ui.presentation.main.MainIntent.OnCardClick
import com.example.recipeapp.ui.presentation.main.MainState.Content
import com.example.recipeapp.ui.presentation.main.MainState.Error
import com.example.recipeapp.ui.presentation.main.MainState.Loading
import com.example.recipeapp.ui.presentation.main.MainState.NavigateToRecipeDetails
import kotlinx.coroutines.launch

class MainViewModel(
    val getRecipeListUseCase: GetRecipeListUseCase
): ViewModel() {
    private val _state: MutableState<MainState> = mutableStateOf(Loading)
    var state: State<MainState> = _state

    init {
        viewModelScope.launch {
            try {
                val recipes = getRecipeListUseCase()
                _state.value = Content(recipes = recipes)
            } catch (ex: Exception) {
                _state.value = Error(ex.message.toString())
            }
        }
    }

    fun accept(intent: MainIntent) {
        when(intent) {
            is OnCardClick -> navigateToRecipeDetails(intent.id)
        }
    }

    private fun navigateToRecipeDetails(id: String) {
        _state.value = NavigateToRecipeDetails(id)
    }
}