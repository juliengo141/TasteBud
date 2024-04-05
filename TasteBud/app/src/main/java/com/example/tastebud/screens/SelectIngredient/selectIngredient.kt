package com.example.tastebud.screens.home

import NavBarScaffold
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tastebud.data.*
import com.example.tastebud.screens.SharedViewModel
import com.example.tastebud.ui.theme.Inter
import com.example.tastebud.ui.theme.TasteBudAccent
import com.example.tastebud.ui.theme.TasteBudBackground
import com.example.tastebud.ui.theme.TasteBudGreen
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

var fridgeIngredients = listOf(
    "Eggs",
    "Chicken",
    "Paneer",
    "Milk",
    "Onions",
    "Rice",
    "Noodles",
    "Tomato",
    "Potatoes",
    "Tuna",
    "Spinach",
    "Pasta",
    "Beef",
    "Honey"
)

@Composable
fun SelectIngredientScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    val selectedIngredients = remember { mutableStateListOf<String>() }
    NavBarScaffold(navController, "What's in the Fridge?") {
        selectContent(
            navController, it, selectedIngredients, sharedViewModel
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun selectContent(
    navController: NavController,
    innerPadding: PaddingValues,
    selectedIngredients: MutableList<String>,
    sharedViewModel: SharedViewModel
) {
    var message by remember { mutableStateOf("") }
    if (sharedViewModel.recipeFound) {
        message = "Choose which ingredients from your fridge you want to use to make your recipe with!"
    } else {
        message =
            "No recipes matching your combination of ingredients found. Please try a different set of ingredients!"
    }
    Column(
        modifier = Modifier.padding(innerPadding).padding(20.dp, 16.dp).background(TasteBudBackground),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = """
                    Choose your Ingredients
                """.trimIndent(),
            fontWeight = FontWeight.Black,
            fontFamily = Inter,
            fontSize = 48.sp,
            lineHeight = 1.3.em,
        )

        FlowRow(
            modifier = Modifier.padding(8.dp, 0.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            for (ingredient in fridgeIngredients) {
                FilterChipExample(title = ingredient, onIngredientSelected = { ingredientSelected ->
                    if (ingredientSelected) {
                        selectedIngredients.add(ingredient)
                    } else {
                        selectedIngredients.remove(ingredient)
                    }
                })
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        var isRecipe: Recipe
        Button(
            onClick = {
                fetchRecipes(selectedIngredients) { ids ->
                    if (ids != null) {
                        // Successfully fetched IDs
                        Log.d("IDs", "$ids")
                        isRecipe = PickIngredientsRecipe(sharedViewModel, ids)
                        Log.d("IS_RECIPE", "${isRecipe}")
                        if (isRecipe.title != "") {
                            sharedViewModel.addRecipe(isRecipe)
                            sharedViewModel.addRecipeFound(true)
                            navController.navigate("recipeDetailsScreen")
                        } else {
                            sharedViewModel.addRecipeFound(false)
                        }
                    } else {
                        // Failed to fetch IDs
                        Log.d("IDs", "Failed to fetch IDs")
                        sharedViewModel.addRecipeFound(false)
                    }
                }
                Log.d("SelectedIngredients", selectedIngredients.toString())
            }, colors = ButtonDefaults.buttonColors(
                containerColor = TasteBudGreen
            ), modifier = Modifier.padding(16.dp).height(40.dp)
        ) {
            Text(
                text = "Continue",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = Inter,
            )
        }
        Text(
            modifier = Modifier.padding(8.dp),
            text = message,
            fontFamily = Inter,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterChipExample(title: String, onIngredientSelected: (Boolean) -> Unit) {
    var selected by remember { mutableStateOf(false) }

    FilterChip(
        onClick = {
            selected = !selected
            onIngredientSelected(selected)
        },
        label = {
            Text(text = title, modifier = Modifier.padding(vertical = 8.dp, horizontal = 10.dp))
        },
        selected = selected,
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = TasteBudAccent
        ),
        leadingIcon = if (selected) {
            {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Done icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else {
            null
        },
    )
}

fun PickIngredientsRecipe(sharedViewModel: SharedViewModel, ids: List<Int>): Recipe {
    val db = FirebaseFirestore.getInstance()
    val docRef = db.collection("Recipes")

    return runBlocking {
        try {
            var documentId: String = "-1"
            for (id in ids) {
                val document = docRef.whereEqualTo("id", id).get().await()
                if (!document.isEmpty) {
                    documentId = document.documents[0].id
                    break
                }
            }

            val docRef1 = db.collection("Recipes").document(documentId)
            val document = docRef1.get().await()
            if (document.exists()) {
                val steps = mutableListOf<Instruction>()
                val testIngredientList = mutableListOf<Ingredient>()
                val ingredients = document.data?.get("extendedIngredients") as List<Map<String, Any>>

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
                Log.d("reachedRecipe", "{${documentId}}")
                Recipe(
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

            } else {
                Log.d("DocumentNotFound", "error")
                Recipe(
                    "",
                    "",
                    "",
                    0,
                    0,
                    listOf(),
                    listOf(),
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    "",
                    mutableListOf(),
                    mutableListOf()
                )
            }
        } catch (e: Exception) {
            Log.e("Error Exception", "Error getting document: $e")
            Recipe(
                "",
                "",
                "",
                0,
                0,
                listOf(),
                listOf(),
                false,
                false,
                false,
                false,
                false,
                false,
                "",
                mutableListOf(),
                mutableListOf()
            )
        }
    }
}
