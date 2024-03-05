package com.example.tastebud.compose.recipeDetail

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
import com.example.tastebud.compose.navBarScaffold.NavBarScaffold

@Composable
fun DietaryRestrictionsScreen(navController: NavController, recipeId: String?) {
    NavBarScaffold(navController) { DietaryRestrictionsContent(navController, recipeId, it) }
}

@Composable
fun DietaryRestrictionsContent(navController: NavController, recipeId: String?, innerPadding: PaddingValues) {
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
        if (recipeId != null) {
            // TODO: using the recipeId, query the database to retrieve the dietaryRestrictions
            val dietaryRestrictions = "vegetarian"
            Text(dietaryRestrictions, modifier = Modifier.padding(15.dp, 0.dp))
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