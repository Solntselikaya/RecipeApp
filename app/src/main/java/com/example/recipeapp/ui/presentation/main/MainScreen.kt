package com.example.recipeapp.ui.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.recipeapp.R
import com.example.recipeapp.ui.navigation.Screen
import com.example.recipeapp.ui.presentation.common.LoadingBar
import com.example.recipeapp.ui.presentation.main.MainState.Content
import com.example.recipeapp.ui.presentation.main.MainState.Failure
import com.example.recipeapp.ui.presentation.main.MainState.Loading
import com.example.recipeapp.ui.presentation.main.components.RecipeCard
import com.example.recipeapp.ui.presentation.main.components.SelectOrderAlertDialog
import com.example.recipeapp.ui.theme.Accent
import com.example.recipeapp.ui.theme.Black
import com.example.recipeapp.ui.theme.Gray
import com.example.recipeapp.ui.theme.Mint
import com.example.recipeapp.ui.theme.Transparent
import com.example.recipeapp.ui.theme.White
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    val viewModel = getViewModel<MainViewModel>()
    val state: MainState by remember { viewModel.state }

    LaunchedEffect(Unit) {
        viewModel.effectFlow.collect { effect ->
            when (effect) {
                is MainEffect.NavigateToDetails -> {
                    navController.navigate(
                        Screen.Details.passRecipeId(effect.id)
                    )
                }
            }
        }
    }

    when (state) {
        is Content                 -> {
            if ((state as Content).isSelectingOrder) {
                SelectOrderAlertDialog(
                    (state as Content).order,
                    { viewModel.accept(MainIntent.OnDialogDismiss) }
                ) { viewModel.accept(MainIntent.OnOrderSelect(it)) }
            }

            Column(
                Modifier
                    .fillMaxSize()
                    .background(Mint)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(Accent),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = (state as Content).search,
                        onValueChange = { viewModel.accept(MainIntent.OnSearchTextChange(it)) },
                        modifier = Modifier
                            .padding(top = 16.dp, start = 8.dp, end = 8.dp, bottom = 8.dp)
                            .fillMaxWidth()
                            .weight(1f),
                        placeholder = {
                            Text(text = "Search")
                        },
                        leadingIcon = {
                            Icon(imageVector = Icons.Default.Search, contentDescription = null)
                        },
                        trailingIcon = {
                            if ((state as Content).search != "") {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .clickable { viewModel.accept(MainIntent.OnSearchTextChange("")) }
                                )
                            }
                        },
                        singleLine = true,
                        maxLines = 1,
                        shape = RoundedCornerShape(16.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Black,
                            containerColor = White,
                            placeholderColor = Gray,
                            focusedIndicatorColor = Transparent,
                            unfocusedIndicatorColor = Transparent,
                            disabledIndicatorColor = Transparent
                        )
                    )

                    IconButton(
                        onClick = { viewModel.accept(MainIntent.OnOrderIconClick) },
                        modifier = Modifier
                            .padding(top = 16.dp, end = 8.dp),
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.page_info),
                            contentDescription = null,
                            tint = White
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .verticalScroll(rememberScrollState())
                ) {
                    if ((state as Content).search != "") {
                        for (item in (state as Content).searchResult) {
                            RecipeCard(
                                title = item.name,
                                description = if (item.description.isNullOrBlank()) stringResource(R.string.no_description) else item.description,
                                image = item.images.first()
                            ) { viewModel.accept(MainIntent.OnCardClick(item.uuid)) }
                        }
                    } else {
                        for (item in (state as Content).recipes) {
                            RecipeCard(
                                title = item.name,
                                description = item.description ?: stringResource(R.string.no_description),
                                image = item.images.first()
                            ) { viewModel.accept(MainIntent.OnCardClick(item.uuid)) }
                        }
                    }
                }
            }
        }

        is Failure                 -> {
            Text(
                text = (state as Failure).message,
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center
            )
        }

        Loading                    -> LoadingBar(Modifier.fillMaxSize())
    }

}