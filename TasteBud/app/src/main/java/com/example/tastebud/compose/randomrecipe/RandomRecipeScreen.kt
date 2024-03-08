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
fun RandomRecipeScreen(navController: NavController) {
    NavBarScaffold(navController) { RandomRecipeContent(navController, it) }
}

@Composable
fun RandomRecipeContent(navController: NavController, innerPadding: PaddingValues) {
    Column(
        modifier = Modifier.padding(innerPadding),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = """
                    This is the Recipe Roulette
                """.trimIndent(),
        )


    }
}