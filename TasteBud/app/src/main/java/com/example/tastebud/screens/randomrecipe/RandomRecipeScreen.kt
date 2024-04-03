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
import com.google.firebase.firestore.FirebaseFirestore
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

    val documentId =  Random.nextInt(0, 324)
    Log.d("myTag", "${documentId}");
    val testIngredientList = mutableListOf<Ingredient>()
    val steps: List<Instruction>
    var pickedRecipe: Recipe

    val db = FirebaseFirestore.getInstance()
    val docRef = db.collection("Recipes").document(documentId.toString())

    // val instructions = document.data?.get("analyzedInstructions") as List<Map<String, Any>>
    // for (instructionData in instructions) {
    //     val stepNum = instructionData["number"] as Int
    //     val step = instructionData["step"].toString()

    //     val ingredient = Ingredient(id, name, og, image, quantity, unit)
    //     ingredientsList.add(ingredient)
    // }

    docRef.get()
        .addOnSuccessListener { document ->
            if (document.exists()) {
                // Document found, you can access its data
                val ingredients = document.data?.get("extendedIngredients") as List<Map<String, Any>>
                for (ingredientData in ingredients) {
                    val name = ingredientData["name"] as String
                    val quantity = ingredientData["amount"].toString()
                    val unit = ingredientData["unit"] as String
                    val id = ingredientData["id"] as String
                    val og = ingredientData["original"] as String
                    val image = ingredientData["image"] as String
                    val ingredient = Ingredient(id, name, og, image, quantity, unit)
                    testIngredientList.add(ingredient)
                }
                pickedRecipe = Recipe(
                    (document.data?.get("id")).toString(),
                    (document.data?.get("title")).toString(),
                    (document.data?.get("image")).toString(),
                    (document.data?.get("readyInMinutes")).toString(),
                    (document.data?.get("servings")) as Int,
                    (document.data?.get("cuisines")) as List<String>,
                    (document.data?.get("vegetarian")) as Boolean,
                    (document.data?.get("vegan")) as Boolean,
                    (document.data?.get("glutenFree")) as Boolean,
                    (document.data?.get("dairyFree")) as Boolean,
                    (document.data?.get("veryHealthy")) as Boolean,
                    (document.data?.get("cheap")) as Boolean,
                    (document.data?.get("difficulty")).toString(),
                    testIngredientList,

                    // (document.data?.get("extendedIngredients")) as List<Ingredient>,
                    (document.data?.get("analyzedInstructions")) as List<Instruction>
                )
                sharedViewModel.addRecipe(pickedRecipe)
                // Do something with the data
                //println("Document data: $data")
            } else {
                println("No such document")
            }
        }
        .addOnFailureListener { exception ->
            println("Error getting documents: $exception")
        }

    // when (randomNumber) {
    //     0 -> {
    //         testIngredientList = listOf(
    //             Ingredient("1", "Flour", "2 cups of flour", "", "2 cups", "cups"),
    //             Ingredient("2", "Cheese", "2 cups of cheese", "", "2 cups", "cups"),
    //             Ingredient("3", "Tomato Sauce", "2 cups of tomato sauce", "", "2 cups", "cups"),
    //             Ingredient("4", "Mushroom", "2 cups of mushroom", "", "2 cups", "cups"),
    //             Ingredient("5", "oil", "2 cups of oil", "", "2 cups", "cups")
    //         )

    //          steps = listOf(
    //             Instruction(1,"Chop pumpkin using a food processor until rice-like.", listOf(
    //                 Ingredient("1", "pumpkin", "2 slices of pumplin", "","2 cups", "cups")
    //             ), listOf(
    //                 Equipment("1","Pan","")
    //             )),
    //             Instruction(1,"Chop pumpkin using a food processor until rice-like.", listOf(
    //                 Ingredient("2", "pumpkin", "2 slices of pumplin", "","2 cups", "cups")
    //             ), listOf(
    //                 Equipment("2","Pan","")
    //             ))
    //         )

    //         pickedRecipe = Recipe(
    //             "656329",
    //             "Pizza bites with pumpkin",
    //             "https://spoonacular.com/recipeImages/656329-312x231.jpg",
    //             "20 min",
    //             2,
    //             listOf("Nordic"),
    //             true,
    //             false,
    //             true,
    //             false,
    //             false,
    //             true,
    //             "Medium",
    //             testIngredientList,
    //             steps,
    //         )
    //     }
    //     1 -> {
    //          testIngredientList = listOf(
    //             Ingredient("1", "Flour", "2 cups of flour", "", "2 cups", "cups"),
    //             Ingredient("2", "Cheese", "2 cups of cheese", "", "2 cups", "cups"),
    //             Ingredient("3", "Tomato Sauce", "2 cups of tomato sauce", "", "2 cups", "cups"),
    //             Ingredient("4", "Mushroom", "2 cups of mushroom", "", "2 cups", "cups"),
    //             Ingredient("5", "oil", "2 cups of oil", "", "2 cups", "cups")
    //         )

    //          steps = listOf(
    //             Instruction(1,"Chop pumpkin using a food processor until rice-like.", listOf(Ingredient("1", "pumpkin", "2 slices of pumplin", "","2 cups", "cups")), listOf(
    //                 Equipment("1","Pan","")
    //             )),
    //             Instruction(1,"Chop pumpkin using a food processor until rice-like.", listOf(Ingredient("2", "pumpkin", "2 slices of pumplin", "","2 cups", "cups")), listOf(
    //                 Equipment("2","Pan","")
    //             ))
    //         )

    //          pickedRecipe = Recipe(
    //             "656329",
    //             "Pumpkin Pizza",
    //             "https://spoonacular.com/recipeImages/656329-312x231.jpg",
    //             "20 min",
    //             2,
    //             listOf("Nordic"),
    //             true,
    //             false,
    //             true,
    //             false,
    //             false,
    //             true,
    //              "Medium",
    //             testIngredientList,
    //             steps,
    //         )}
    //         else -> {
    //             testIngredientList = listOf(
    //                 Ingredient("1", "Flour", "2 cups of flour", "", "2 cups", "cups"),
    //                 Ingredient("2", "Cheese", "2 cups of cheese", "", "2 cups", "cups"),
    //                 Ingredient("3", "Tomato Sauce", "2 cups of tomato sauce", "", "2 cups", "cups"),
    //                 Ingredient("4", "Mushroom", "2 cups of mushroom", "", "2 cups", "cups"),
    //                 Ingredient("5", "oil", "2 cups of oil", "", "2 cups", "cups")
    //             )

    //             steps = listOf(
    //                 Instruction(1,"Chop pumpkin using a food processor until rice-like.", listOf(Ingredient("1", "pumpkin", "2 slices of pumplin", "","2 cups", "cups")), listOf(
    //                     Equipment("1","Pan","")
    //                 )),
    //                 Instruction(1,"Chop pumpkin using a food processor until rice-like.", listOf(Ingredient("2", "pumpkin", "2 slices of pumplin", "","2 cups", "cups")), listOf(
    //                     Equipment("2","Pan","")
    //                 ))
    //             )

    //             pickedRecipe = Recipe(
    //                 "656329",
    //                 "Pepperoni Pizza",
    //                 "https://spoonacular.com/recipeImages/656329-312x231.jpg",
    //                 "20 min",
    //                 2,
    //                 listOf("Nordic"),
    //                 true,
    //                 false,
    //                 true,
    //                 false,
    //                 false,
    //                 true,
    //                 "Easy",
    //                 testIngredientList,
    //                 steps,
    //             )
    //         }
    //     }

    }