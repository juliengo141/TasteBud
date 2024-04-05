package com.example.tastebud.screens.home

import NavBarScaffold
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import com.example.tastebud.data.Recipe
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.tastebud.data.Equipment
import com.example.tastebud.data.Ingredient
import com.example.tastebud.screens.SharedViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.tastebud.R
import com.example.tastebud.data.Instruction
import com.example.tastebud.ui.theme.Inter
import com.example.tastebud.ui.theme.TasteBudBackground
import com.example.tastebud.ui.theme.TasteBudCard
import com.example.tastebud.ui.theme.TasteBudGreen
import com.example.tastebud.ui.theme.TasteBudOrange
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await


data class Dish(val name: String, val cuisine: String, val imageUri: String)

@Composable
fun HomeScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    NavBarScaffold(navController, "Home") { HomeContent(navController, it, sharedViewModel) }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(navController: NavController, innerPadding: PaddingValues, sharedViewModel: SharedViewModel) {

    val indianNo = 29
    val italianNo = 120
    val chineseNo = 92
    val mexicanNo = 187

    val recipe1 = getRecipe(indianNo)
    val recipe2 = getRecipe(italianNo)
    val recipe3 = getRecipe(chineseNo)
    val recipe4 = getRecipe(mexicanNo)

    val dishes = listOf(
        recipe1,
        recipe2,
        recipe3,
        recipe4
    )

    val imageSize = 75.dp
    val horizontalSpacing = 4.dp

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(innerPadding),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
                .height(190.dp),
            colors = CardDefaults.cardColors(containerColor = TasteBudGreen),
            onClick = {
                navController.navigate("CuisineSelectionScreen")
            }
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.foodjourney),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(8.dp)
                        .border(1.dp, Color.Black)
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "RECIPE ROAD:",
                        fontFamily = Inter,
                        fontWeight = FontWeight.Black,
                        color = TasteBudBackground,
                        fontSize = 24.sp,
                        modifier = Modifier
                            .padding(8.dp),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "RESUME YOUR JOURNEY",
                        fontFamily = Inter,
                        fontWeight = FontWeight.Black,
                        color = TasteBudOrange,
                        fontSize = 24.sp,
                        modifier = Modifier
                            .padding(8.dp),
                        textAlign = TextAlign.Center
                    )

                }

            }
        }

        Text(
            text = "OUR WEEKLY PICKS:",
            fontFamily = Inter,
            fontWeight = FontWeight.Black,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, top = 8.dp),
            textAlign = TextAlign.Center
        )


        dishes.forEachIndexed() { index, dish ->
            Card(
                colors = CardDefaults.cardColors(containerColor = TasteBudCard),
                elevation = CardDefaults.cardElevation(10.dp),
                onClick = {
                    sharedViewModel.addRecipe(dish)
                    navController.navigate("recipeDetailsScreen")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 6.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .size(36.dp)
                            .background(TasteBudGreen, shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${index + 1}",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Black,
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                    Spacer(modifier = Modifier.width(horizontalSpacing))
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = dish.title,
                            style = MaterialTheme.typography.bodyLarge,
                            fontFamily = Inter,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = dish.cuisines[0],
                            fontFamily = Inter,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodySmall,
                            color = TasteBudOrange
                        )
                    }
                    Spacer(modifier = Modifier.width(horizontalSpacing))
                    Image(
                        painter = rememberAsyncImagePainter(dish.imageUrl),
                        contentDescription = null,
                        modifier = Modifier.size(imageSize),
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center
                    )
                }
            }
        }
    }
}

@Composable
fun getRecipe(documentId: Int): Recipe {
    val db = FirebaseFirestore.getInstance()
    val docRef = db.collection("Recipes").document(documentId.toString())
    return runBlocking {
        try {
            val document = docRef.get().await()
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
