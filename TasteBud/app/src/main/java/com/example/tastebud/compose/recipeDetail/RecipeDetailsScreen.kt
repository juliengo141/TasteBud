package com.example.tastebud.compose.recipeDetail

import NavBarScaffold
import android.os.Bundle
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun RecipeDetailScreen(navController: NavController) {
    NavBarScaffold(navController) { RecipeDetailContent(navController, it) }
}

@Composable
fun RecipeDetailContent(navController: NavController, innerPadding: PaddingValues) {
    Column(
        modifier = Modifier.padding(innerPadding),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        val testIngredientList = listOf(
            Ingredient("1", "Flour", "2 cups of flour", "", "2 cups", "cups"),
            Ingredient("2", "Cheese", "2 cups of cheese", "", "2 cups", "cups"),
            Ingredient("3", "Tomato Sauce", "2 cups of tomato sauce", "", "2 cups", "cups"),
            Ingredient("4", "Mushroom", "2 cups of mushroom", "", "2 cups", "cups"),
            Ingredient("5", "oil", "2 cups of oil", "", "2 cups", "cups")
        )

        val steps = listOf(
            listOf("1", "Chop pumpkin using a food processor until rice-like."),
            listOf("2", "Saut pumpkin in hot olive oil for 3 minutes. Set aside and let cool."),
            listOf("3", "Mix feta and mozzarella; add, one at a time, eggs."),
            listOf("4", "Mix and combine."),
            listOf("5", "Add pumpkin and spices, mix well until well blended."),
            listOf(
                "6",
                "Evenly spoon the mixture into the greased muffin tin molds. Press pizza dough down evenly and firmly (the pressing down firmly is very important to make sure they stick together)."
            ),
            listOf("7", "Place in the oven and bake for 30 minutes at 200C."),
            listOf(
                "8",
                "Remove the pizza bites from the oven and let set until cool (this is also very important  let the pizza bites set in their pan for 5  10 minutes before removing  if you take them out while they are too hot they will break)."
            )
        )

        val testRecipe = Recipe(
            "656329",
            "Pizza bites with pumpkin",
            "https://spoonacular.com/recipeImages/656329-312x231.jpg",
            "20 min",
            true,
            false,
            true,
            false,
            testIngredientList,
            steps,

            )

        RecipeInfo(testRecipe)
        IngredientsCard(testRecipe.ingredients)
        Button(onClick = {
            navController.navigate("dietaryRestrictionsScreen/${testRecipe.recipeId}")
        }) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "Dietary Restrictions and Substitutions",
            )
        }
        Button(onClick = {
            navController.navigate("flashcardsScreen/${testRecipe.recipeId}")
        }) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "Start",
            )
        }
    }
}

@Composable
fun RecipeInfo(recipe: Recipe) {
    Text(recipe.title, modifier = Modifier.padding(15.dp, 0.dp), fontSize = 20.sp, fontWeight = FontWeight.Bold)
    Text("Estimated Time: " + recipe.estimatedTime, modifier = Modifier.padding(15.dp, 0.dp))
    AsyncImage(
        model = recipe.imageUrl,
        contentDescription = "Translated description of what the image contains",
        modifier = Modifier.padding(15.dp, 0.dp)
        // TODO: add a placeholder and error image
    )
}

@Composable
fun IngredientsCard(ingredients: List<Ingredient>) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(15.dp).clickable { },
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            Text("Ingredients", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            IngredientsList(ingredients)
        }
    }
}

@Composable
fun IngredientsList(ingredients: List<Ingredient>) {
    LazyColumn {
        items(ingredients) { ingredient ->
            IngredientItem(ingredient)
        }
    }
}

@Composable
fun IngredientItem(ingredient: Ingredient) {
    Column {
        Text(ingredient.original)
    }
}