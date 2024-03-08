package com.example.tastebud.compose.home

import NavBarScaffold
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    NavBarScaffold(navController) { HomeContent(navController, it) }
}

@Composable
fun HomeContent(navController: NavController, innerPadding: PaddingValues) {
    Column(
        modifier = Modifier.padding(innerPadding),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = """
                    This is the HOMESCREEN
                """.trimIndent(),
        )
        var text = remember { mutableStateOf("Recipe") }
        Button(onClick = {
            navController.navigate("recipeDetailsScreen")
        }) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = text.value,
            )
        }
    }
}
