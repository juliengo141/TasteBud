package com.example.tastebud.screens.home

import NavBarScaffold
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tastebud.screens.SharedViewModel
import com.example.tastebud.data.Equipment
import com.example.tastebud.data.Ingredient
import com.example.tastebud.data.Instruction
import com.example.tastebud.data.Recipe

@Composable
fun HomeScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    NavBarScaffold(navController, "Home") { HomeContent(navController, it, sharedViewModel ) }
}

@Composable
fun HomeContent(navController: NavController, innerPadding: PaddingValues, sharedViewModel: SharedViewModel) {
    CreateRecipe(sharedViewModel = sharedViewModel)
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

@Composable
fun CreateRecipe(sharedViewModel: SharedViewModel){
    val testIngredientList = listOf(
        Ingredient("1", "Flour", "2 cups of flour", "", "2 cups", "cups"),
        Ingredient("2", "Cheese", "2 cups of cheese", "", "2 cups", "cups"),
        Ingredient("3", "Tomato Sauce", "2 cups of tomato sauce", "", "2 cups", "cups"),
        Ingredient("4", "Mushroom", "2 cups of mushroom", "", "2 cups", "cups"),
        Ingredient("5", "oil", "2 cups of oil", "", "2 cups", "cups")
    )

    val steps = listOf(
        Instruction(1,"Chop pumpkin using a food processor until rice-like.", listOf(Ingredient("1", "pumpkin", "2 slices of pumplin", "","2 cups", "cups")), listOf(
            Equipment("1","Pan","")
        )),
        Instruction(1,"Chop pumpkin using a food processor until rice-like.", listOf(Ingredient("2", "pumpkin", "2 slices of pumplin", "","2 cups", "cups")), listOf(
            Equipment("2","Pan","")
        ))
    )

    val testRecipe = Recipe(
        "656329",
        "Pizza bites with pumpkin",
        "https://spoonacular.com/recipeImages/656329-312x231.jpg",
        "20 min",
        2,
        listOf("Nordic"),
        true,
        false,
        true,
        false,
        false,
        true,
        "Medium",
        testIngredientList,
        steps,
    )
    sharedViewModel.addRecipe(testRecipe)
}
