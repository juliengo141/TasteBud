package com.example.tastebud.compose.home

import NavBarScaffold
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun RandomRecipeScreen(navController: NavController) {
    NavBarScaffold(navController) { RandomRecipeContent(navController, it) }
}

@Composable
fun RandomRecipeContent(navController: NavController, innerPadding: PaddingValues) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            modifier = Modifier.padding(8.dp)
                .fillMaxWidth(),

            text = """
                    Not sure what to make? Let us decide for you!
                """.trimIndent(), fontWeight = FontWeight.Bold, color = Color.Green, fontSize = 24.sp,
            textAlign = TextAlign.Center,
        )
        Button(
            onClick = {
                //Call flashcards Tinder Swipe
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green,
                contentColor = Color.Black
            ),
            modifier = Modifier.padding(24.dp).height(52.dp)
        ) {
            Text("Generate Recipe...", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        }
    }
}