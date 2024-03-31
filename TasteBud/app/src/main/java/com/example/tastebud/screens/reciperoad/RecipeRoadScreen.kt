package com.example.tastebud.screens.reciperoad


import NavBarScaffold
import android.util.Log
import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tastebud.R
import com.example.tastebud.data.Equipment
import com.example.tastebud.data.Ingredient
import com.example.tastebud.data.Instruction
import com.example.tastebud.data.Recipe
import com.example.tastebud.screens.SharedViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects

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
        LazyColumn() {
            items(CuisineRecipes(sharedViewModel.cuisine)) { recipe ->
                RecipeCard(recipe = recipe, navController, sharedViewModel)
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
            .height(200.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = {
            sharedViewModel.addRecipe(recipe)
            navController.navigate("recipeDetailsScreen")
        },
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.chickentikka),
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
                    text = "Difficulty: ${recipe.difficulty}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}

fun CuisineRecipes(cuisine: String): List<Recipe> {
    val db = Firebase.firestore
    var recipes = mutableListOf<Recipe>()

    val recipesRef = db.collection("Recipes")
    val filteredRecipes = recipesRef.whereArrayContains("cuisines", cuisine)
        .get()
        .addOnSuccessListener { result ->
            for (document in result) {
                Log.d("RECIPE_DB", "${document.id} => ${document.data}")
            }
            var test = result.toObjects<Any>().toMutableList()
            Log.d("TEST", "${test}")
        }
        .addOnFailureListener { exception ->
            Log.w("RECIPE_DB", "Error getting documents.", exception)
        }

    return recipes
}

fun setRecipes(): List<Recipe> {
    val ingredientList1 = listOf(
        Ingredient("1", "Basmati Rice", "2 cups of basmati rice", "", "2 cups", "cups"),
        Ingredient("2", "Water", "4 cups of water", "", "4 cups", "cups"),
        Ingredient("3", "Salt", "1 teaspoon of salt", "", "1 teaspoon", "teaspoon"),
        Ingredient("4", "Cumin Seeds", "1 tablespoon of cumin seeds", "", "1 tablespoon", "tablespoon"),
        Ingredient("5", "Ghee", "1 tablespoon of ghee", "", "1 tablespoon", "tablespoon")
    )

    val steps1 = listOf(
        Instruction(
            1, "Rinse the rice under cold water until the water runs clear.", listOf(
                Ingredient("1", "Basmati Rice", "2 cups of basmati rice", "", "2 cups", "cups")
            ), listOf(
                Equipment("1", "Strainer", "")
            )
        ),
        Instruction(
            2,
            "In a large saucepan, heat ghee over medium heat. Add cumin seeds and let them sizzle for 1 minute.",
            listOf(
                Ingredient("4", "Cumin Seeds", "1 tablespoon of cumin seeds", "", "1 tablespoon", "tablespoon"),
                Ingredient("5", "Ghee", "1 tablespoon of ghee", "", "1 tablespoon", "tablespoon")
            ),
            listOf(
                Equipment("2", "Saucepan", "")
            )
        )
    )

    val recipe1 = Recipe(
        "1",
        "Cumin Rice (Jeera Rice)",
        "https://www.kitchensanctuary.com/wp-content/uploads/2020/07/Chicken-Tikka-Skewers-square-FS-77.jpg",
        "25 min",
        4,
        listOf("Indian"),
        true,
        false,
        true,
        false,
        false,
        true,
        "Easy",
        ingredientList1,
        steps1
    )

    val ingredientList2 = listOf(
        Ingredient("1", "Chickpeas", "1 can of chickpeas, drained and rinsed", "", "1 can", "can"),
        Ingredient("2", "Onion", "1 large onion, finely chopped", "", "1", ""),
        Ingredient("3", "Tomatoes", "2 tomatoes, chopped", "", "2", ""),
        Ingredient("4", "Ginger", "1 tablespoon of grated ginger", "", "1 tablespoon", "tablespoon"),
        Ingredient("5", "Garlic", "3 cloves of garlic, minced", "", "3 cloves", "")
    )

    val steps2 = listOf(
        Instruction(
            1, "Heat oil in a pan over medium heat. Add onions and cook until golden brown.", listOf(
                Ingredient("2", "Onion", "1 large onion, finely chopped", "", "1", "")
            ), listOf(
                Equipment("1", "Pan", "")
            )
        ),
        Instruction(
            2, "Add ginger and garlic, cook for another minute until fragrant.", listOf(
                Ingredient("4", "Ginger", "1 tablespoon of grated ginger", "", "1 tablespoon", "tablespoon"),
                Ingredient("5", "Garlic", "3 cloves of garlic, minced", "", "3 cloves", "")
            ), emptyList()
        )
    )

    val recipe2 = Recipe(
        "2",
        "Chana Masala",
        "https://www.kitchensanctuary.com/wp-content/uploads/2020/07/Chicken-Tikka-Skewers-square-FS-77.jpg",
        "40 min",
        4,
        listOf("Indian"),
        true,
        false,
        true,
        false,
        false,
        true,
        "Easy",
        ingredientList2,
        steps2
    )

    val ingredientList3 = listOf(
        Ingredient("1", "Paneer", "250g paneer, cubed", "", "250g", "grams"),
        Ingredient("2", "Yogurt", "1/2 cup of yogurt", "", "1/2 cup", "cup"),
        Ingredient("3", "Garam Masala", "1 teaspoon of garam masala", "", "1 teaspoon", "teaspoon"),
        Ingredient("4", "Turmeric Powder", "1/2 teaspoon of turmeric powder", "", "1/2 teaspoon", "teaspoon"),
        Ingredient("5", "Coriander Leaves", "2 tablespoons of chopped coriander leaves", "", "2 tablespoons", "tablespoons")
    )

    val steps3 = listOf(
        Instruction(1, "Marinate paneer cubes with yogurt, garam masala, turmeric powder, and salt. Let it sit for 30 minutes.", listOf(
            Ingredient("1", "Paneer", "250g paneer, cubed", "", "250g", "grams"),
            Ingredient("2", "Yogurt", "1/2 cup of yogurt", "", "1/2 cup", "cup"),
            Ingredient("3", "Garam Masala", "1 teaspoon of garam masala", "", "1 teaspoon", "teaspoon"),
            Ingredient("4", "Turmeric Powder", "1/2 teaspoon of turmeric powder", "", "1/2 teaspoon", "teaspoon")
        ), emptyList()),
        Instruction(2, "Heat oil in a pan over medium heat. Add marinated paneer and cook until golden brown.", listOf(
            Ingredient("1", "Paneer", "250g paneer, cubed", "", "250g", "grams")
        ), listOf(
            Equipment("1", "Pan", "")
        ))
    )

    val recipe3 = Recipe(
        "3",
        "Paneer Tikka",
        "https://www.kitchensanctuary.com/wp-content/uploads/2020/07/Chicken-Tikka-Skewers-square-FS-77.jpg",
        "40 min",
        4,
        listOf("Indian"),
        true,
        false,
        true,
        false,
        false,
        true,
        "Medium",
        ingredientList3,
        steps3
    )
    val ingredientList4 = listOf(
        Ingredient("1", "Lentils", "1 cup of lentils (masoor dal)", "", "1 cup", "cup"),
        Ingredient("2", "Onion", "1 large onion, finely chopped", "", "1", ""),
        Ingredient("3", "Tomatoes", "2 tomatoes, chopped", "", "2", ""),
        Ingredient("4", "Turmeric Powder", "1/2 teaspoon of turmeric powder", "", "1/2 teaspoon", "teaspoon"),
        Ingredient("5", "Cumin Seeds", "1 teaspoon of cumin seeds", "", "1 teaspoon", "teaspoon")
    )

    val steps4 = listOf(
        Instruction(1, "Rinse the lentils under cold water until the water runs clear. Drain and set aside.", listOf(
            Equipment("1", "Strainer", "")
        ), emptyList()),
        Instruction(2, "Heat oil in a pressure cooker. Add cumin seeds and let them sizzle for a few seconds.", listOf(
            Equipment("1", "Strainer", "")
        ), listOf(
            Equipment("1", "Pressure Cooker", "")
        )),
        Instruction(3, "Add chopped onions and sauté until golden brown.", listOf(
            Equipment("1", "Strainer", "")
        ), emptyList()),
        Instruction(4, "Add chopped tomatoes, turmeric powder, and salt. Cook until tomatoes are soft and oil separates.", listOf(
            Equipment("1", "Strainer", ""),
                    Equipment("1", "Strainer", "")
        ), emptyList()),
        Instruction(5, "Add rinsed lentils and water. Close the lid and pressure cook for 4 whistles.", listOf(
            Equipment("1", "Strainer", "")
        ), listOf(
            Equipment("1", "Pressure Cooker", "")
        )),
        Instruction(6, "Once done, let the pressure release naturally. Serve hot with rice or roti.", emptyList(), emptyList())
    )

    val recipe4 = Recipe(
        "5",
        "Masoor Dal (Red Lentil Curry)",
        "https://www.kitchensanctuary.com/wp-content/uploads/2020/07/Chicken-Tikka-Skewers-square-FS-77.jpg",
        "35 min",
        4,
        listOf("Indian"),
        true,
        false,
        true,
        false,
        false,
        true,
        "Medium",
        ingredientList4,
        steps4
    )

    val ingredientList5 = listOf(
        Ingredient("1", "Chicken", "500g chicken breast, diced", "", "500g", "grams"),
        Ingredient("2", "Yogurt", "1/2 cup of yogurt", "", "1/2 cup", "cup"),
        Ingredient("3", "Ginger-Garlic Paste", "2 tablespoons of ginger-garlic paste", "", "2 tablespoons", "tablespoons"),
        Ingredient("4", "Turmeric Powder", "1 teaspoon of turmeric powder", "", "1 teaspoon", "teaspoon"),
        Ingredient("5", "Red Chili Powder", "1 teaspoon of red chili powder", "", "1 teaspoon", "teaspoon"),
        Ingredient("6", "Garam Masala", "1 teaspoon of garam masala", "", "1 teaspoon", "teaspoon"),
        Ingredient("7", "Coriander Powder", "1 teaspoon of coriander powder", "", "1 teaspoon", "teaspoon"),
        Ingredient("8", "Salt", "1 teaspoon of salt", "", "1 teaspoon", "teaspoon"),
        Ingredient("9", "Lemon Juice", "2 tablespoons of lemon juice", "", "2 tablespoons", "tablespoons"),
        Ingredient("10", "Cooking Oil", "2 tablespoons of cooking oil", "", "2 tablespoons", "tablespoons"),
        Ingredient("11", "Coriander Leaves", "2 tablespoons of chopped coriander leaves", "", "2 tablespoons", "tablespoons")
    )

    val steps5 = listOf(
        Instruction(1, "In a large bowl, mix yogurt, ginger-garlic paste, turmeric powder, red chili powder, garam masala, coriander powder, salt, and lemon juice.", listOf(
            Equipment("1", "Strainer", ""),
            Equipment("1", "Strainer", ""),
            Equipment("1", "Strainer", ""),
            Equipment("1", "Strainer", ""),
            Equipment("1", "Strainer", ""),
            Equipment("1", "Strainer", ""),
            Equipment("1", "Strainer", ""),
            Equipment("1", "Strainer", ""),
        ), emptyList()),
        Instruction(2, "Add diced chicken pieces to the marinade. Coat the chicken well with the marinade. Cover and refrigerate for at least 1 hour, or overnight for best results.", listOf(
            Equipment("1", "Strainer", ""),
        ), emptyList()),
        Instruction(3, "Heat oil in a pan over medium-high heat. Add marinated chicken pieces and cook until golden brown and cooked through, about 8-10 minutes.", listOf(
            Equipment("1", "Strainer", ""),
        ), listOf(
            Equipment("1", "Pan", "")
        )),
        Instruction(4, "Garnish with chopped coriander leaves before serving. Serve hot with rice, naan, or roti.", listOf(
            Equipment("1", "Strainer", ""),
        ), emptyList())
    )

    val recipe5 = Recipe(
        "6",
        "Chicken Tikka",
        "https://www.kitchensanctuary.com/wp-content/uploads/2020/07/Chicken-Tikka-Skewers-square-FS-77.jpg",
        "1 hr 20 min",
        4,
        listOf("Indian"),
        true,
        false,
        true,
        false,
        false,
        true,
        "Hard",
        ingredientList5,
        steps5
    )

    val sampleRecipes = listOf(recipe1, recipe2, recipe3, recipe4, recipe5)
    return sampleRecipes
}
