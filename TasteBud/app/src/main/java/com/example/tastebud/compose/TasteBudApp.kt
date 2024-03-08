package com.example.tastebud.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tastebud.compose.home.HomeScreen
import com.example.tastebud.compose.home.ProfileScreen
import com.example.tastebud.compose.home.RandomRecipeScreen
import com.example.tastebud.compose.home.SelectIngredientScreen
import com.example.tastebud.compose.recipeDetail.DietaryRestrictionsScreen
import com.example.tastebud.compose.recipeDetail.FlashcardsScreen
import com.example.tastebud.compose.recipeDetail.RecipeDetailScreen

@Composable
fun TasteBudApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "homeScreen") {
        composable("homeScreen") {
            HomeScreen(navController = navController)
        }
        composable("RandomRecipeScreen") {
            RandomRecipeScreen(navController = navController)
        }
        composable("SelectIngredientScreen") {
            SelectIngredientScreen(navController = navController)
        }
        composable("profileScreen") {
            ProfileScreen(navController = navController)
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
