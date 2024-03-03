package com.example.tastebud.compose.navBarScaffold

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavBarScaffold(navController: NavController, createContent: @Composable (PaddingValues) -> Unit) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text("TasteBud")
        })
    }, bottomBar = {
        BottomAppBar(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.primary,
        ) {
            var text = remember { mutableStateOf("Home") }
            Button(onClick = {
                navController.navigate("homeScreen")
            }) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = text.value,
                )
            }
        }
    }) { innerPadding ->
        createContent(innerPadding)
    }
}

