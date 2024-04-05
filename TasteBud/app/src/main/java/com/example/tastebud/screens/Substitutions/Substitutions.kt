package com.example.tastebud.screens.home

import NavBarScaffold
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    NavBarScaffold(navController, "Substitutions List") { innerPadding ->
        Column(modifier = Modifier.fillMaxSize()) {
            SubstitutionsContent(navController, innerPadding, sharedViewModel)
        }
    }
}

@Composable
fun SubstitutionsContent(navController: NavController, innerPadding: PaddingValues, sharedViewModel: SharedViewModel) {
    val listItems = listOf(
        "Baking Powder (1 teaspoon) --> 1/4 teaspoon baking soda + 1/4 teaspoon cornstarch + 1/2 teaspoon of cream of tartar",
        "Baking Soda (1 teaspoon) --> 4 teaspoons baking powder",
        "Brown Sugar (1 cup) --> 1 cup white sugar",
        "Cocoa (1/4 cup) --> 1 square/ounce unsweetened chocolate",
        "Salted Butter (1 cup) --> 1 cup margarine OR 7/8 cup vegetable oil + 1/2 teaspoon salt",
        "Unsalted Butter (1 cup) --> 1 cup shortening OR 7/8 cup vegetable oil",
        "Buttermilk (1 cup) --> 1 cup yogurt",
        "Half and Half Cream (1 cup) --> 7/8 cup milk + 1 tablespoon butter",
        "Light Cream (1 cup) --> 3/4 cup milk + 3 tablespoons butter",
        "Cream of Tartar (1 teaspoon) --> 2 teaspoons lemon juice or vinegar",
        "Egg (1 whole) --> 2 1/2 tablespoons of powdered egg substitute plus 2 1/2 tablespoons water OR 1/4 cup liquid egg substitute OR 1/4 cup silken tofu pureed OR 3 tablespoons mayonnaise OR half a banana mashed with 1/2 teaspoon baking powder",
        "Garlic (1 clove) --> 1/8 teaspoon garlic powder",
        "Honey (1 cup) --> 1 1/4 cup white sugar + 1/3 cup water",
        "Lemon Juice (1 teaspoon) --> 1/2 teaspoon vinegar)",
        "Mayonnaise (1 cup) --> 1 cup sour cream OR 1 cup plain yogurt"
    )
    IconButton(
        onClick = { navController.popBackStack() }, modifier = Modifier.padding(top = 58.dp)
    ) {
        Icon(
            Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color.Black // Specify the color of the icon here
        )
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(bottom = 12.dp), verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            Text(
                text = "Baking Ingredient Substitutions",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            )
        }
        listItems.forEachIndexed { index, item ->
            if (index == 4) {
                item {
                    Text(
                        text = "Dairy Substitutions",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                    )
                }
            }
            if (index == 12) {
                item {
                    Text(
                        text = "Other Substitutions",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                    )
                }
            }
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(start = 16.dp)
                ) {
                    Box(
                        modifier = Modifier.size(8.dp).background(TasteBudGreen).align(Alignment.Top)
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
}
