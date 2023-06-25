package com.example.recipeapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.recipeapp.common.Constants.RECIPE_ID
import com.example.recipeapp.ui.presentation.main.MainScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        composable(route = Screen.Main.route) {
            MainScreen(navController = navController)
        }

        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument(RECIPE_ID) {
                    type = NavType.StringType
                }
            )
        ) {

        }
    }
}