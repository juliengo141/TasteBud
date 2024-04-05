package com.example.tastebud.screens.reciperoad


import NavBarScaffold
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.tastebud.components.DifficultyStars
import com.example.tastebud.data.Equipment
import com.example.tastebud.data.Ingredient
import com.example.tastebud.data.Instruction
import com.example.tastebud.data.Recipe
import com.example.tastebud.screens.SharedViewModel
import com.example.tastebud.ui.theme.TasteBudGreen
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

@Composable
fun RecipeRoadScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    NavBarScaffold(navController, "Recipe Road") { RecipeRoadContent(navController, it, sharedViewModel) }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeRoadContent(navController: NavController, innerPadding: PaddingValues, sharedViewModel: SharedViewModel) {

    Column(
        modifier = Modifier
            .padding(innerPadding)
    ) {
        Text(
            text = "Road Across " + sharedViewModel.country, fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )
        CuisineRecipes(sharedViewModel)
        Log.d("RECIPE_DB_RECIPES", "${sharedViewModel.cuisineRecipes}")
        LazyColumn() {
            items(sharedViewModel.cuisineRecipes) { recipe ->
                if (recipe != null) {
                    RecipeCard(recipe = recipe, navController, sharedViewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeCard(recipe: Recipe, navController: NavController, sharedViewModel: SharedViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(220.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = {
            sharedViewModel.addRecipe(recipe)
            navController.navigate("recipeDetailsScreen")
        },
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = recipe.imageUrl,
                contentDescription = null,
                modifier = Modifier.size(200.dp)
                //
            )
            Column {
                Text(
                    text = recipe.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(10.dp)
                )
                Text(
                    text = "Time: ${recipe.estimatedTime}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Servings: ${recipe.servings}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Difficulty:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(Modifier.padding(15.dp, 0.dp)) {
                    DifficultyStars(recipe.difficulty.toFloatOrNull()?.toInt() ?: 0)
                }
            }
        }
    }
}

fun CuisineRecipes(sharedViewModel: SharedViewModel){
    val db = Firebase.firestore
    var recipes = mutableListOf<Recipe>()
    val testIngredientList = mutableListOf<Ingredient>()

    val recipesRef = db.collection("Recipes")
    recipesRef.whereArrayContains("cuisines", sharedViewModel.cuisine)
        .orderBy("difficulty")
        .limit(5)
        .get()
        .addOnSuccessListener { result ->
            for (document in result) {
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
                        if(name == null){
                            name =""
                        }
                        if(unit == null){
                            unit =""
                        }
                        if(og == null){
                            og =""
                        }
                        if(image == null){
                            image =""
                        }
                        val ingredient = Ingredient(id, name, og, image, quantity, unit)
                        testIngredientList.add(ingredient)
                    }
                    val steps = mutableListOf<Instruction>()

                    val instructions = document.data?.get("analyzedInstructions") as List<Map<String, Any>>
                    for(instructionData in instructions) {
                        val stepNum = instructionData["number"] as Long
                        val step = instructionData["step"].toString()

                        val equipmentList = mutableListOf<Equipment>()
                        //val equipment = instructionData.data?.get("equipment") as List<Map<String, Any>>
                        val equipment = instructionData["equipment"] as List<Map<String, Any>>
                        for(equipmentData in equipment) {
                            val id = equipmentData["id"].toString()
                            val name = equipmentData["name"].toString()
                            val image = equipmentData["image"].toString()
                            val e = Equipment(id, name, image)
                            equipmentList.add(e)
                        }

                        val instructionIngredientsList = mutableListOf<Equipment>()
                        val i = instructionData["ingredients"] as List<Map<String, Any>>
                        for(instructionIngredientData in i) {
                            val id = instructionIngredientData["id"].toString()
                            val name = instructionIngredientData["name"].toString()
                            val image = instructionIngredientData["image"].toString()
                            val e = Equipment(id, name, image)
                            instructionIngredientsList.add(e)
                        }

                        val instruction = Instruction(stepNum, step, instructionIngredientsList, equipmentList)
                        steps.add(instruction)

                    }

                    var recipe = Recipe(
                        (document.data?.get("id")).toString(),
                        (document.data?.get("title")).toString(),
                        (document.data?.get("image")).toString(),
                        (document.data?.get("readyInMinutes")).toString() + " mins",
                        (document.data?.get("servings")) as Long,
                        (document.data?.get("cuisines")) as List<String>,
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
                    Log.d("RECIPE_DB", "${recipe}")
                    recipes.add(recipe)
                    Log.d("RECIPE_DB1", "${recipes}")
                }
            }
            sharedViewModel.addCuisineRecipes(recipes)
        }
        .addOnFailureListener { exception ->
            Log.w("RECIPE_DB", "Error getting documents.", exception)
        }
}

