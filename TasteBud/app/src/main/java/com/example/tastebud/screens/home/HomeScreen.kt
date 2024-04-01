package com.example.tastebud.screens.home

import NavBarScaffold
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import com.example.tastebud.data.Recipe

import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.tastebud.data.Equipment
import com.example.tastebud.data.Ingredient
import com.example.tastebud.screens.SharedViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import com.example.tastebud.data.Instruction


data class Dish(val name: String, val cuisine: String, val imageUri: String)

@Composable
fun HomeScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    NavBarScaffold(navController, "Home") { HomeContent(navController, it, sharedViewModel) }
}


@Composable
fun HomeContent(navController: NavController, innerPadding: PaddingValues, sharedViewModel: SharedViewModel) {
    CreateRecipe(sharedViewModel)
    val dishes = listOf(
        Dish("Pani Puri", "INDIAN", "https://upload.wikimedia.org/wikipedia/commons/a/a0/Dahi_puri%2C_Doi_phuchka.jpg"),
        Dish("Gnocchi", "ITALIAN", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/89/Vegan_gnocchi_arrabbiata.jpg/640px-Vegan_gnocchi_arrabbiata.jpg"),
        Dish("Chow Mein", "CHINESE", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a6/Homemade_Chow_mein_with_shrimps_and_meat_with_a_choy_and_Choung.jpg/640px-Homemade_Chow_mein_with_shrimps_and_meat_with_a_choy_and_Choung.jpg"),
        Dish("Baklava", "MIDDLE EASTERN", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c7/Baklava%281%29.png/640px-Baklava%281%29.png")
    )

    val imageSize = 75.dp // Set a fixed size for the images
    val horizontalSpacing = 4.dp

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(innerPadding),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        androidx.compose.material3.Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(200.dp),
            elevation = CardDefaults.cardElevation(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            onClick = {
                navController.navigate("CuisineSelectionScreen")
            },
        )
        Text(
            text = "OUR WEEKLY PICKS:",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, top = 8.dp),
            textAlign = TextAlign.Center
        )


        dishes.forEachIndexed() {index, dish ->
            Row(
                verticalAlignment = Alignment.CenterVertically, // Center vertically within the row
                horizontalArrangement = Arrangement.Start, // Align content to the start horizontally
                modifier = Modifier.fillMaxWidth() // Expand the row to fill the width
            ) {
                Text(
                    text = "${index + 1}.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 8.dp),
                    textAlign = TextAlign.Center


                )
                Spacer(modifier = Modifier.width(horizontalSpacing))
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center, // Center vertically within the column
                    horizontalAlignment = Alignment.CenterHorizontally // Center horizontally within the column
                ) {
                    Text(text = dish.name, style = MaterialTheme.typography.bodyLarge)
                    Text(
                        text = dish.cuisine,
                        style = MaterialTheme.typography.bodySmall.copy(color = Color(0xFFD88C45))
                    )
                }
                Spacer(modifier = Modifier.width(horizontalSpacing))
                Image(
                    painter = rememberAsyncImagePainter(dish.imageUri),
                    contentDescription = null,
                    modifier = Modifier.size(imageSize) ,
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )
            }
        }
    }
}

@Composable
fun Card(
    modifier: Modifier = Modifier,
    elevation: Dp = CardDefaults.elevation(),
    backgroundColor: Color = MaterialTheme.colors.surface,
    content: @Composable () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.clickable(onClick = onClick),
        elevation = elevation,
        backgroundColor = backgroundColor,
        content = content
    )
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


