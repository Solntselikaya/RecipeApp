package com.example.recipeapp.ui.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.recipeapp.R
import com.example.recipeapp.ui.navigation.Screen
import com.example.recipeapp.ui.presentation.common.LoadingBar
import com.example.recipeapp.ui.presentation.main.MainState.Content
import com.example.recipeapp.ui.presentation.main.MainState.Error
import com.example.recipeapp.ui.presentation.main.MainState.Loading
import com.example.recipeapp.ui.presentation.main.MainState.NavigateToRecipeDetails
import com.example.recipeapp.ui.presentation.main.components.RecipeCard
import com.example.recipeapp.ui.theme.Mint
import org.koin.androidx.compose.getViewModel

@Composable
fun MainScreen(navController: NavController) {
    val viewModel = getViewModel<MainViewModel>()
    val state: MainState by remember { viewModel.state }

    when (state) {
        is Content                 -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Mint)
            ) {
                items(items = (state as Content).recipes) {item ->
                    RecipeCard(
                        title = item.name,
                        description = item.description ?: stringResource(R.string.no_description),
                        image = item.images.first()
                    ) { viewModel.accept(MainIntent.OnCardClick(item.uuid)) }
                }
            }
        }

        is Error                   -> {
            Text(
                text = (state as Error).message,
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center
            )
        }

        Loading                    -> LoadingBar(Modifier.fillMaxSize())

        is NavigateToRecipeDetails -> {
            navController.navigate(
                Screen.Details.passRecipeId((state as NavigateToRecipeDetails).id)
            )
        }
    }


}