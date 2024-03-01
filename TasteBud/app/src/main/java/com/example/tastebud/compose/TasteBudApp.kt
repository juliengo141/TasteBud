package com.example.tastebud.compose

import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tastebud.compose.home.HomeScreen
import com.example.tastebud.compose.recipeDetail.RecipeDetailScreen

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
    }
}

