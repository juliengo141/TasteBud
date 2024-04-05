package com.example.tastebud.screens.home

import NavBarScaffold
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tastebud.R
import com.example.tastebud.data.Equipment
import com.example.tastebud.data.Ingredient
import com.example.tastebud.data.Instruction
import com.example.tastebud.data.Recipe
import com.example.tastebud.screens.SharedViewModel
import com.example.tastebud.ui.theme.TasteBudGreen
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.random.Random


@Composable
fun RandomRecipeScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    NavBarScaffold(navController, "Recipe Roulette") { RandomRecipeContent(navController, it, sharedViewModel) }
}

@Composable
fun RandomRecipeContent(navController: NavController, innerPadding: PaddingValues, sharedViewModel: SharedViewModel) {
    PickRandomRecipe(sharedViewModel = sharedViewModel)
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.reciperoulette),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = Modifier.padding(8.dp).fillMaxWidth(),

                text = """
                    Not sure what to make? Let us decide for you!
                """.trimIndent(),
                fontWeight = FontWeight.Black,
                color = Color.White,
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                style = LocalTextStyle.current.merge(
                    androidx.compose.ui.text.TextStyle(
                        lineHeight = 1.5.em,
                    )
                )
            )
            Button(
                onClick = {
                    navController.navigate("recipeDetailsScreen")
                }, colors = ButtonDefaults.buttonColors(
                    containerColor = TasteBudGreen, contentColor = Color.White
                ), modifier = Modifier.padding(24.dp).height(52.dp)
            ) {
                Text("Generate Recipe...", fontWeight = FontWeight.Bold, fontSize = 24.sp)
            }
        }
    }
}

@Composable
fun PickRandomRecipe(sharedViewModel: SharedViewModel) {

    val documentId = Random.nextInt(0, 324)
    Log.d("myTag", "${documentId}")
    val testIngredientList = mutableListOf<Ingredient>()
    var pickedRecipe: Recipe

    val db = FirebaseFirestore.getInstance()
    val docRef = db.collection("Recipes").document(documentId.toString())

    docRef.get().addOnSuccessListener { document ->
        if (document.exists()) {
            val ingredients = document.data?.get("extendedIngredients") as List<Map<String, Any>>
            //For ingredients mapping
            for (ingredientData in ingredients) {
                var name = ingredientData["name"] as? String
                val quantity = ingredientData["amount"].toString()
                var unit = ingredientData["unit"] as? String
                val id = ingredientData["id"].toString()
                var og = ingredientData["original"] as? String
                var image = ingredientData["image"] as? String
                if (name == null) {
                    name = ""
                }
                if (unit == null) {
                    unit = ""
                }
                if (og == null) {
                    og = ""
                }
                if (image == null) {
                    image = ""
                }
                val ingredient = Ingredient(id, name, og, image, quantity, unit)
                testIngredientList.add(ingredient)
            }
            val steps = mutableListOf<Instruction>()

            val instructions = document.data?.get("analyzedInstructions") as List<Map<String, Any>>
            for (instructionData in instructions) {
                val stepNum = instructionData["number"] as Long
                val step = instructionData["step"].toString()

                val equipmentList = mutableListOf<Equipment>()
                val equipment = instructionData["equipment"] as List<Map<String, Any>>
                for (equipmentData in equipment) {
                    val id = equipmentData["id"].toString()
                    val name = equipmentData["name"].toString()
                    val image = equipmentData["image"].toString()
                    val e = Equipment(id, name, image)
                    equipmentList.add(e)
                }

                val instructionIngredientsList = mutableListOf<Equipment>()
                val i = instructionData["ingredients"] as List<Map<String, Any>>
                for (instructionIngredientData in i) {
                    val id = instructionIngredientData["id"].toString()
                    val name = instructionIngredientData["name"].toString()
                    val image = instructionIngredientData["image"].toString()
                    val e = Equipment(id, name, image)
                    instructionIngredientsList.add(e)
                }

                val instruction = Instruction(stepNum, step, instructionIngredientsList, equipmentList)
                steps.add(instruction)

            }

            pickedRecipe = Recipe(
                (document.data?.get("id")).toString(),
                (document.data?.get("title")).toString(),
                (document.data?.get("image")).toString(),
                (document.data?.get("readyInMinutes")) as Long,
                (document.data?.get("servings")) as Long,
                (document.data?.get("cuisines")) as List<String>,
                (document.data?.get("diets")) as List<String>,
                (document.data?.get("vegetarian")) as Boolean,
                (document.data?.get("vegan")) as Boolean,
                (document.data?.get("glutenFree")) as Boolean,
                (document.data?.get("dairyFree")) as Boolean,
                (document.data?.get("veryHealthy")) as Boolean,
                (document.data?.get("cheap")) as Boolean,
                (document.data?.get("difficulty")).toString(),
                testIngredientList,
                steps
            )
            sharedViewModel.addRecipe(pickedRecipe)
        } else {
            println("No such document")
        }
    }.addOnFailureListener { exception ->
        println("Error getting documents: $exception")
    }
}
