package com.example.tastebud.compose

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    val route: String,
    val navArguments: List<NamedNavArgument> = emptyList()
) {
    object Home : Screen("home")

    object RecipeDetail : Screen(
        route = "recipeDetail",
        navArguments = listOf(navArgument("recipeId") {
            type = NavType.StringType
        })
    ) {
        fun createRoute() = "recipeDetail"
    }
}