package com.example.tastebud.screens.home

import NavBarScaffold
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tastebud.screens.SharedViewModel
import com.example.tastebud.data.Equipment
import com.example.tastebud.data.Ingredient
import com.example.tastebud.data.Instruction
import com.example.tastebud.data.Recipe
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

data class Dish(val name: String, val cuisine: String)

@Composable
fun HomeScreen(navController: NavController, sharedViewModel: SharedViewModel) {
<<<<<<< Updated upstream:TasteBud/app/src/main/java/com/example/tastebud/screens/home/HomeScreen.kt
    NavBarScaffold(navController, "Home") { HomeContent(navController, it, sharedViewModel ) }
=======
    NavBarScaffold(navController) {
        Column {
            //ResumeJourneyScreen(navController)
            HomeContent(navController, it, sharedViewModel)
        }
    }
>>>>>>> Stashed changes:TasteBud/app/src/main/java/com/example/tastebud/compose/home/HomeScreen.kt
}

@Composable
fun ResumeJourneyScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Resume Your Journey: Italian Cooking",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.clickable {
               // navController.navigate("your_destination_screen_route")
            }
        )
    }
}

@Composable
fun HomeContent(navController: NavController, innerPadding: PaddingValues, sharedViewModel: SharedViewModel) {
    CreateRecipe(sharedViewModel = sharedViewModel)
    val dishes = listOf(
        Dish("Pani Puri", "Indian"),
        Dish("Chow Mein", "Chinese"),
        Dish("Baklava", "Middle Eastern"),
        Dish("Gnocchi", "Italian")
    )


        AsyncImage(
            model = recipe.imageUrl,
            contentDescription = "Translated description of what the image contains",
            modifier = Modifier.padding(15.dp, 0.dp)
            // TODO: add a placeholder and error image
        )
    }


    Column(
        modifier = Modifier.padding(innerPadding),`
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = "Our Weekly Picks:",
            style = MaterialTheme.typography.headlineMedium,
           // verticalArrangement = Arrangement.Center,
          //  horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(start = 8.dp, top = 8.dp)
        )

        dishes.forEachIndexed { index, dish ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "${index + 1}.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = dish.name,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = dish.cuisine,
                        style = MaterialTheme.typography.labelMedium.copy(color = Color.Red),
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }
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
