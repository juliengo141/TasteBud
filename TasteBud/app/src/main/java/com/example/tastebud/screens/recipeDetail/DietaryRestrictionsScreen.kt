package com.example.tastebud.screens.recipeDetail

import NavBarScaffold
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tastebud.screens.SharedViewModel

@Composable
fun DietaryRestrictionsScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    NavBarScaffold(navController, "Dietary Restrictions") {
        DietaryRestrictionsContent(
            navController,
            it,
            sharedViewModel
        )
    }
}

@Composable
fun DietaryRestrictionsContent(
    navController: NavController,
    innerPadding: PaddingValues,
    sharedViewModel: SharedViewModel
) {
    Column(
        modifier = Modifier.padding(innerPadding),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            "Dietary Restrictions",
            modifier = Modifier.padding(15.dp, 0.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        if (sharedViewModel.recipe != null) {
            val vegetarian: Boolean = true
            val vegan: Boolean = false
            val glutenFree: Boolean = true
            val dairyFree: Boolean = false
            if (vegetarian) {
                Text("Vegetarian", modifier = Modifier.padding(15.dp, 0.dp))
            }
            if (vegan) {
                Text("Vegan", modifier = Modifier.padding(15.dp, 0.dp))
            }
            if (glutenFree) {
                Text("Gluten free", modifier = Modifier.padding(15.dp, 0.dp))
            }
            if (dairyFree) {
                Text("Dairy Free", modifier = Modifier.padding(15.dp, 0.dp))
            }
        }
        Button(onClick = {
            navController.navigate("recipeDetailsScreen")
        }) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "Back",
            )
        }
    }
}
