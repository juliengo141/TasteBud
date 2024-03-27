package com.example.tastebud.screens.home

import NavBarScaffold
import android.util.Log
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
import com.example.tastebud.screens.SharedViewModel
import com.example.tastebud.data.Equipment
import com.example.tastebud.data.Ingredient
import com.example.tastebud.data.Instruction
import com.example.tastebud.data.Recipe
import kotlin.random.Random


@Composable
fun RandomRecipeScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    NavBarScaffold(navController, "Recipe Roulette") { RandomRecipeContent(navController, it, sharedViewModel) }
}

@Composable
fun RandomRecipeContent(navController: NavController, innerPadding: PaddingValues, sharedViewModel: SharedViewModel) {
    PickRandomRecipe(sharedViewModel = sharedViewModel)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),

            text = """
                    Not sure what to make? Let us decide for you!
                """.trimIndent(), fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 24.sp,
            textAlign = TextAlign.Center,
        )
        Button(
            onClick = {
                navController.navigate("recipeDetailsScreen")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Cyan,
                contentColor = Color.Black
            ),
            modifier = Modifier
                .padding(24.dp)
                .height(52.dp)
        ) {
            Text("Generate Recipe...", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        }
    }
}

@Composable
fun PickRandomRecipe(sharedViewModel: SharedViewModel){

    val randomNumber =  Random.nextInt(0, 3)
    Log.d("myTag", "${randomNumber}");
    val testIngredientList: List<Ingredient>
    val steps: List<Instruction>
    val pickedRecipe: Recipe
    when (randomNumber) {
        0 -> {
            testIngredientList = listOf(
                Ingredient("1", "Flour", "2 cups of flour", "", "2 cups", "cups"),
                Ingredient("2", "Cheese", "2 cups of cheese", "", "2 cups", "cups"),
                Ingredient("3", "Tomato Sauce", "2 cups of tomato sauce", "", "2 cups", "cups"),
                Ingredient("4", "Mushroom", "2 cups of mushroom", "", "2 cups", "cups"),
                Ingredient("5", "oil", "2 cups of oil", "", "2 cups", "cups")
            )

             steps = listOf(
                Instruction(1,"Chop pumpkin using a food processor until rice-like.", listOf(
                    Ingredient("1", "pumpkin", "2 slices of pumplin", "","2 cups", "cups")
                ), listOf(
                    Equipment("1","Pan","")
                )),
                Instruction(1,"Chop pumpkin using a food processor until rice-like.", listOf(
                    Ingredient("2", "pumpkin", "2 slices of pumplin", "","2 cups", "cups")
                ), listOf(
                    Equipment("2","Pan","")
                ))
            )

            pickedRecipe = Recipe(
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
        }
        1 -> {
             testIngredientList = listOf(
                Ingredient("1", "Flour", "2 cups of flour", "", "2 cups", "cups"),
                Ingredient("2", "Cheese", "2 cups of cheese", "", "2 cups", "cups"),
                Ingredient("3", "Tomato Sauce", "2 cups of tomato sauce", "", "2 cups", "cups"),
                Ingredient("4", "Mushroom", "2 cups of mushroom", "", "2 cups", "cups"),
                Ingredient("5", "oil", "2 cups of oil", "", "2 cups", "cups")
            )

             steps = listOf(
                Instruction(1,"Chop pumpkin using a food processor until rice-like.", listOf(Ingredient("1", "pumpkin", "2 slices of pumplin", "","2 cups", "cups")), listOf(
                    Equipment("1","Pan","")
                )),
                Instruction(1,"Chop pumpkin using a food processor until rice-like.", listOf(Ingredient("2", "pumpkin", "2 slices of pumplin", "","2 cups", "cups")), listOf(
                    Equipment("2","Pan","")
                ))
            )

             pickedRecipe = Recipe(
                "656329",
                "Pumpkin Pizza",
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
            )}
            else -> {
                testIngredientList = listOf(
                    Ingredient("1", "Flour", "2 cups of flour", "", "2 cups", "cups"),
                    Ingredient("2", "Cheese", "2 cups of cheese", "", "2 cups", "cups"),
                    Ingredient("3", "Tomato Sauce", "2 cups of tomato sauce", "", "2 cups", "cups"),
                    Ingredient("4", "Mushroom", "2 cups of mushroom", "", "2 cups", "cups"),
                    Ingredient("5", "oil", "2 cups of oil", "", "2 cups", "cups")
                )

                steps = listOf(
                    Instruction(1,"Chop pumpkin using a food processor until rice-like.", listOf(Ingredient("1", "pumpkin", "2 slices of pumplin", "","2 cups", "cups")), listOf(
                        Equipment("1","Pan","")
                    )),
                    Instruction(1,"Chop pumpkin using a food processor until rice-like.", listOf(Ingredient("2", "pumpkin", "2 slices of pumplin", "","2 cups", "cups")), listOf(
                        Equipment("2","Pan","")
                    ))
                )

                pickedRecipe = Recipe(
                    "656329",
                    "Pepperoni Pizza",
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
                    "Easy",
                    testIngredientList,
                    steps,
                )
            }
        }
    sharedViewModel.addRecipe(pickedRecipe)
    }