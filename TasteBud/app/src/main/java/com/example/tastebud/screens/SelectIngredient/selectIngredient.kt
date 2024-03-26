package com.example.tastebud.screens.home

import NavBarScaffold

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tastebud.screens.SharedViewModel


@Composable
fun SelectIngredientScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    NavBarScaffold(navController, "What's in the Fridge") { selectContent(navController, it) }
}

@Composable
fun selectContent(navController: NavController, innerPadding: PaddingValues) {
    Column(
        modifier = Modifier.padding(innerPadding),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = """
                    This is the FRIDGE
                """.trimIndent(),
        )


    }
}
