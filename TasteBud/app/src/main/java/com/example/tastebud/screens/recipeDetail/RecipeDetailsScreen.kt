package com.example.tastebud.screens.recipeDetail

import NavBarScaffold
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.tastebud.components.DifficultyStars
import com.example.tastebud.screens.SharedViewModel
import com.example.tastebud.data.Ingredient
import com.example.tastebud.data.Recipe
import com.example.tastebud.ui.theme.TasteBudAccent
import com.example.tastebud.ui.theme.TasteBudBackground
import com.example.tastebud.ui.theme.TasteBudGreen
import com.example.tastebud.ui.theme.TasteBudOrange
import com.example.tastebud.ui.theme.TasteBudRed

@Composable
fun RecipeDetailScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    NavBarScaffold(navController, "Recipe Details") { RecipeDetailContent(navController, it, sharedViewModel) }
}

@Composable
fun RecipeDetailContent(navController: NavController, innerPadding: PaddingValues, sharedViewModel: SharedViewModel) {
    Column(
        modifier = Modifier.padding(innerPadding),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        sharedViewModel.recipe?.let { RecipeInfo(it) }
        sharedViewModel.recipe?.let { IngredientsCard(it.ingredients) }

        // Bottom Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Not Today Button
            Button(
                modifier = Modifier
                    .weight(1f)
                    .size(48.dp)
                    .background(TasteBudRed, CircleShape),
                onClick = {navController.popBackStack()},
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = TasteBudRed)
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Close",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }

            // Let's Cook Button
            Button(
                modifier = Modifier
                    .weight(1f)
                    .size(48.dp)
                    .background(Color.Green, CircleShape),
                onClick = {navController.navigate("flashcardsScreen")},
                colors = ButtonDefaults.buttonColors(
                    containerColor = TasteBudGreen)
            ) {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = "Check",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }


        }
//        Button(onClick = {
//            navController.navigate("dietaryRestrictionsScreen")
//        },colors = ButtonDefaults.buttonColors(
//            containerColor = TasteBudOrange
//        )) {
//            Text(
//                modifier = Modifier.fillMaxWidth(),
//                textAlign = TextAlign.Center,
//                text = "Dietary Restrictions and Substitutions",
//            )
//        }
    }
}

@Composable
fun RecipeInfo(recipe: Recipe) {
    Text(text = "Let's Make: ${recipe.title}",  Modifier.padding(25.dp, 10.dp), fontSize = 20.sp, fontWeight = FontWeight.Bold, maxLines = 2, overflow = TextOverflow.Ellipsis)
    AsyncImage(
        model = recipe.imageUrl,
        contentDescription = "Translated description of what the image contains",
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 0.dp)
            .height(200.dp)
            .fillMaxWidth()
        // TODO: add a placeholder and error image
    )
    Row(Modifier.padding(15.dp, 0.dp)) {
        Icon(Icons.Filled.Fastfood, contentDescription = null, modifier = Modifier.size(25.dp), tint = TasteBudGreen)
        Text("Cuisine: " + recipe.cuisines.joinToString(), modifier = Modifier.padding(start = 10.dp), fontWeight = FontWeight.Bold, fontSize = 20.sp)
    }
    Row(Modifier.padding(15.dp, 0.dp)) {
        Icon(Icons.Filled.Timer, contentDescription = null, modifier = Modifier.size(25.dp),tint = TasteBudGreen)
        Text("Time to Cook: " + recipe.estimatedTime, modifier = Modifier.padding(start = 10.dp), fontWeight = FontWeight.Bold, fontSize = 20.sp)
    }
    Row(Modifier.padding(15.dp, 0.dp)) {
        Icon(Icons.Filled.Star, contentDescription = null, modifier = Modifier.size(25.dp), tint = TasteBudGreen)
        Text("Difficulty: ", modifier = Modifier.padding(start = 10.dp), fontWeight = FontWeight.Bold, fontSize = 20.sp)
        val difficulty = recipe.difficulty.toFloatOrNull()?.toInt() ?: 0
        DifficultyStars(difficulty)
    }
}

@Composable
fun IngredientsCard(ingredients: List<Ingredient>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(175.dp)
            .padding(15.dp, 5.dp)
            .clickable { },
        colors = CardDefaults.cardColors(TasteBudAccent)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text("Ingredients", fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(4.dp))
            IngredientsList(ingredients)
        }
    }
}


@Composable
fun IngredientsList(ingredients: List<Ingredient>) {
    LazyColumn(modifier = Modifier.padding(8.dp),) {
        items(ingredients) { ingredient ->
            IngredientItem(ingredient)
        }
    }
}

@Composable
fun IngredientItem(ingredient: Ingredient) {
    Column {
        Text(ingredient.original, fontFamily = FontFamily.SansSerif, fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
}