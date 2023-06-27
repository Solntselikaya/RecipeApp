package com.example.recipeapp.ui.presentation.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.domain.usecase.GetRecipeListUseCase
import com.example.recipeapp.ui.presentation.main.MainIntent.OnCardClick
import com.example.recipeapp.ui.presentation.main.MainIntent.OnDialogDismiss
import com.example.recipeapp.ui.presentation.main.MainIntent.OnOrderIconClick
import com.example.recipeapp.ui.presentation.main.MainIntent.OnOrderSelect
import com.example.recipeapp.ui.presentation.main.MainIntent.OnSearchTextChange
import com.example.recipeapp.ui.presentation.main.MainState.Content
import com.example.recipeapp.ui.presentation.main.MainState.Failure
import com.example.recipeapp.ui.presentation.main.MainState.Loading
import com.example.recipeapp.ui.presentation.main.OrderingType.DateAscending
import com.example.recipeapp.ui.presentation.main.OrderingType.DateDescending
import com.example.recipeapp.ui.presentation.main.OrderingType.Default
import com.example.recipeapp.ui.presentation.main.OrderingType.NameAscending
import com.example.recipeapp.ui.presentation.main.OrderingType.NameDescending
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel(
    val getRecipeListUseCase: GetRecipeListUseCase
): ViewModel() {
    private val _state: MutableState<MainState> = mutableStateOf(Loading)
    val state: State<MainState> = _state

    private val _effectFlow = MutableSharedFlow<MainEffect>()
    val effectFlow = _effectFlow.asSharedFlow()

    private fun onSearchTextChange(input: String) {
        if (input != "") {
            val recipes = (_state.value as Content).recipes
            _state.value = (_state.value as Content).copy(
                search = input,
                searchResult = recipes.filter { it.doesMatchSearchQuery(input) }
            )
        } else {
            _state.value = (_state.value as Content).copy(search = "")
        }
    }

    init {
        getAllData()
    }

    fun accept(intent: MainIntent) {
        when(intent) {
            is OnCardClick        -> emitEffect(MainEffect.NavigateToDetails(intent.id))
            is OnSearchTextChange -> onSearchTextChange(intent.input)
            OnOrderIconClick      -> openSelectOrderingDialog()
            is OnOrderSelect      -> setNewOrder(intent.order)
            OnDialogDismiss       -> dismissDialog()
        }
    }

    private fun dismissDialog() {
        _state.value = (_state.value as Content).copy(isSelectingOrder = false)
    }

    private fun setNewOrder(order: OrderingType) {
        when (order) {
            Default        -> {
                _state.value = (_state.value as Content).copy(order = order)
            }

            NameAscending  -> {
                _state.value = (_state.value as Content).copy(
                    recipes = (_state.value as Content).recipes.sortedBy { it.name },
                    searchResult = (_state.value as Content).searchResult.sortedBy { it.name },
                    order = order
                )
            }

            NameDescending -> {
                _state.value = (_state.value as Content).copy(
                    recipes = (_state.value as Content).recipes.sortedByDescending { it.name },
                    searchResult = (_state.value as Content).searchResult.sortedByDescending { it.name },
                    order = order
                )
            }

            DateAscending  -> {
                _state.value = (_state.value as Content).copy(
                    recipes = (_state.value as Content).recipes.sortedBy { it.lastUpdated },
                    searchResult = (_state.value as Content).searchResult.sortedBy { it.lastUpdated },
                    order = order
                )
            }

            DateDescending -> {
                _state.value = (_state.value as Content).copy(
                    recipes = (_state.value as Content).recipes.sortedByDescending { it.lastUpdated },
                    searchResult = (_state.value as Content).searchResult.sortedByDescending { it.lastUpdated },
                    order = order
                )
            }
        }

        dismissDialog()
    }

    private fun openSelectOrderingDialog() {
        _state.value = (_state.value as Content).copy(isSelectingOrder = true)
    }

    private fun emitEffect(effect: MainEffect) {
        viewModelScope.launch { _effectFlow.emit(effect) }
    }

    private fun getAllData() {
        viewModelScope.launch {
            try {
                val recipes = getRecipeListUseCase()
                _state.value = Content(recipes = recipes)
            } catch (ex: Exception) {
                _state.value = Failure(ex.message.toString())
            }
        }
    }

}