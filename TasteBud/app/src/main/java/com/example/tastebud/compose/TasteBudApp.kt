package com.example.tastebud.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tastebud.compose.home.HomeScreen
import com.example.tastebud.compose.recipeDetail.DietaryRestrictionsScreen
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
        composable("dietaryRestrictionsScreen") {
            DietaryRestrictionsScreen(navController = navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarForScaffold() {
    TopAppBar(
        title = {
            Text("TasteBud")
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBarForScaffold(navController: NavController) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary,
    ) {
        Button(onClick = {
            navController.navigate("homeScreen")
        }) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "Home",
            )
        }
    }
}
