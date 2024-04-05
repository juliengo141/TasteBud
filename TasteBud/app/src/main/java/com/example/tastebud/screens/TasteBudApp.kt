package com.example.tastebud.screens


import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tastebud.screens.home.*
import com.example.tastebud.screens.recipeDetail.DietaryRestrictionsScreen
import com.example.tastebud.screens.recipeDetail.FlashcardsScreen
import com.example.tastebud.screens.recipeDetail.RecipeDetailScreen
import com.example.tastebud.screens.reciperoad.CuisineSelectionScreen
import com.example.tastebud.screens.reciperoad.RecipeRoadScreen
import com.example.tastebud.screens.signUp.SignUpScreen

@Composable
fun TasteBudApp() {
    val sharedViewModel: SharedViewModel = viewModel()
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "signInScreen") {
        composable("signUpScreen") {
            SignUpScreen(navController = navController, sharedViewModel)
        }
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
        composable("RecipeRoadScreen") {
            RecipeRoadScreen(navController = navController, sharedViewModel)
        }
        composable("profileScreen") {
            ProfileScreen(navController = navController, sharedViewModel)
        }
        composable("recipeDetailsScreen") {
            RecipeDetailScreen(navController = navController, sharedViewModel)
        }
        composable("CuisineSelectionScreen") {
            CuisineSelectionScreen(navController = navController, sharedViewModel)
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
        composable(
            "SubstitutionsScreen"
        ) {
            SubstitutionsScreen(navController = navController, sharedViewModel)
        }
        composable(
            "UserDietaryRestrictions"
        ) {
            UserDietaryRestrictionsScreen(navController = navController, sharedViewModel)
        }
    }
}
