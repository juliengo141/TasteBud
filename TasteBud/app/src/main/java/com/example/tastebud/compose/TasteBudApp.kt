package com.example.tastebud.compose


import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tastebud.compose.home.HomeScreen
import com.example.tastebud.compose.home.ProfileScreen
import com.example.tastebud.compose.home.RandomRecipeScreen
import com.example.tastebud.compose.home.SelectIngredientScreen
import com.example.tastebud.compose.home.SignInScreen
import com.example.tastebud.compose.recipeDetail.DietaryRestrictionsScreen
import com.example.tastebud.compose.recipeDetail.FlashcardsScreen
import com.example.tastebud.compose.recipeDetail.RecipeDetailScreen

@Composable
fun TasteBudApp() {
    val sharedViewModel: SharedViewModel = viewModel()
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "signInScreen") {
        composable("signInScreen") {
            SignInScreen(navController = navController, sharedViewModel)
        }
        composable("homeScreen") {
            HomeScreen(navController = navController, sharedViewModel)
        }
        composable("RandomRecipeScreen") {
            RandomRecipeScreen(navController = navController, sharedViewModel)
        }
        composable("SelectIngredientScreen") {
            SelectIngredientScreen(navController = navController, sharedViewModel)
        }
        composable("profileScreen") {
            ProfileScreen(navController = navController, sharedViewModel)
        }
        composable("recipeDetailsScreen") {
            RecipeDetailScreen(navController = navController, sharedViewModel)
        }
        composable(
            "dietaryRestrictionsScreen"
        ) {
            DietaryRestrictionsScreen(navController = navController, sharedViewModel)
        }
        composable(
            "flashcardsScreen"
        ) {
            FlashcardsScreen(navController = navController, sharedViewModel)
        }
    }
}
