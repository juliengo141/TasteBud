package com.example.tastebud.screens.home

import NavBarScaffold
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.example.tastebud.screens.SharedViewModel
import com.example.tastebud.ui.theme.TasteBudGreen


@Composable
fun SubstitutionsScreen(navController: NavController, sharedViewModel: SharedViewModel) {
   NavBarScaffold(navController, "Substitutions List") { SubstitutionsContent(navController, it, sharedViewModel) }
}
    
@Composable
fun SubstitutionsContent(navController: NavController, innerPadding: PaddingValues, sharedViewModel: SharedViewModel) {
    val listItems = listOf(
        "Baking Powder (1 teaspoon) --> 1/4 teaspoon baking soda + 1/4 teaspoon cornstarch + 1/2 teaspoon of cream of tartar",
        "Baking Soda (1 teaspoon) --> 4 teaspoons baking powder",
        "Brown Sugar (1 cup) --> 1 cup white sugar",
        "Salted Butter (1 cup) --> 1 cup margarine OR 7/8 cup vegetable oil + 1/2 teaspoon salt",
        "Unsalted Butter (1 cup) --> 1 cup shortening OR 7/8 cup vegetable oil",
        "Buttermilk (1 cup) --> 1 cup yogurt",
        "Cocoa (1/4 cup) --> 1 square/ounce unsweetened chocolate",
        "Half and Half Cream (1 cup) --> 7/8 cup milk + 1 tablespoon butter",
        "Light Cream (1 cup) --> 3/4 cup milk + 3 tablespoons butter",
        "Cream of Tartar (1 teaspoon) --> 2 teaspoons lemon juice or vinegar",
        "Egg (1 whole) --> 2 1/2 tablespoons of powdered egg substitute plus 2 1/2 tablespoons water OR 1/4 cup liquid egg substitute OR 1/4 cup silken tofu pureed OR 3 tablespoons mayonnaise OR half a banana mashed with 1/2 teaspoon baking powder",
        "Garlic (1 clove) --> 1/8 teaspoon garlic powder",
        "Honey (1 cup) --> 1 1/4 cup white sugar + 1/3 cup water",
        "Lemon Juice (1 teaspoon) --> 1/2 teaspoon vinegar)",
        "Mayonnaise (1 cup) --> 1 cup sour cream OR 1 cup plain yogurt"
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = innerPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(listItems) { index, item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(TasteBudGreen)
                        .align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = item,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}