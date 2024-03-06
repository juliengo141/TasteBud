package com.example.tastebud.compose

import RecipeDetailScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tastebud.compose.home.HomeScreen
import com.example.tastebud.compose.recipeDetail.DietaryRestrictionsScreen
import com.example.tastebud.compose.recipeDetail.FlashcardsScreen


@Composable
fun TasteBudApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "homeScreen") {
        composable("homeScreen") {
            HomeScreen(navController = navController)
        }
        composable("recipeDetailsScreen") {
            RecipeDetailScreen(navController = navController)
        }
        composable(
            "dietaryRestrictionsScreen/{recipeId}",
            arguments = listOf(navArgument("recipeId") { type = NavType.StringType })
        ) { backStackEntry ->
            DietaryRestrictionsScreen(navController = navController, backStackEntry.arguments?.getString("recipeId"))
        }
        composable(
            "flashcardsScreen/{recipeId}",
            arguments = listOf(navArgument("recipeId") { type = NavType.StringType })
        ) { backStackEntry ->
            FlashcardsScreen(navController = navController, backStackEntry.arguments?.getString("recipeId"))
        }
    }
}
