package com.example.tastebud.compose.recipeDetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.tastebud.data.Ingredient
import com.example.tastebud.data.Recipe

@Composable
fun DietaryRestrictionsScreen(navController: NavController) {
    ScaffoldDietaryRestrictions(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldDietaryRestrictions(navController: NavController) {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text("TasteBud")
//                }
//            )
//        },
//        bottomBar = {
//            BottomAppBar(
//                containerColor = MaterialTheme.colorScheme.primaryContainer,
//                contentColor = MaterialTheme.colorScheme.primary,
//            ) {
//                Button(onClick = {
//                    navController.navigate("homeScreen")
//                }) {
//                    Text(
//                        modifier = Modifier
//                            .fillMaxWidth(),
//                        textAlign = TextAlign.Center,
//                        text = "Home",
//                    )
//                }
//            }
//        },
//    ) { innerPadding ->
//        Column(
//            modifier = Modifier
//                .padding(innerPadding),
//            verticalArrangement = Arrangement.spacedBy(16.dp),
//        ) {
//            RecipeInfo()
//            IngredientsCard()
//            Button(onClick = {
//                navController.navigate("homeScreen")
//            }) {
//                Text(
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                    textAlign = TextAlign.Center,
//                    text = "Home",
//                )
//            }
//        }
//    }
}

//@Composable
//fun RecipeInfo() {
//    // dummy data for Recipe
//    val testRecipe = Recipe("656329", "Pizza bites with pumpkin", "https://spoonacular.com/recipeImages/656329-312x231.jpg", "20 min")
//
//    Text(testRecipe.name, modifier = Modifier.padding(15.dp, 0.dp), fontSize = 20.sp, fontWeight = FontWeight.Bold)
//    Text("Estimated Time: " + testRecipe.estimatedTime, modifier = Modifier.padding(15.dp, 0.dp))
//    AsyncImage(
//        model = testRecipe.imageUrl,
//        contentDescription = "Translated description of what the image contains",
//        modifier = Modifier.padding(15.dp, 0.dp)
//        // TODO: add a placeholder and error image
//    )
//}
//
//@Composable
//fun IngredientsCard() {
//    Card(
//        modifier = Modifier.fillMaxWidth().padding(15.dp).clickable { },
//    ) {
//        Column(modifier = Modifier.padding(15.dp)) {
//            Text("Ingredients", fontSize = 20.sp, fontWeight = FontWeight.Bold)
//
//            // dummy data for IngredientList
//            val testIngredientList = listOf(
//                Ingredient("1", "Flour", "2 cups of flour", "","2 cups", "cups"),
//                Ingredient("2", "Cheese", "2 cups of cheese", "","2 cups", "cups"),
//                Ingredient("3", "Tomato Sauce", "2 cups of tomato sauce", "","2 cups", "cups"),
//                Ingredient("4", "Mushroom", "2 cups of mushroom", "","2 cups", "cups"),
//                Ingredient("5", "oil", "2 cups of oil", "","2 cups", "cups")
//            )
//
//            IngredientsList(testIngredientList)
//        }
//    }
//}
//
//@Composable
//fun IngredientsList( ingredients: List<Ingredient>) {
//    LazyColumn {
//        items(ingredients) { ingredient ->
//            IngredientItem(ingredient)
//        }
//    }
//}
//
//@Composable
//fun IngredientItem(ingredient: Ingredient) {
//    Column {
//        Text(ingredient.original)
//    }
//}