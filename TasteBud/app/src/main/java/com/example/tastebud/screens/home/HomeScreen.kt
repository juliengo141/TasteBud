package com.example.tastebud.screens.home

import NavBarScaffold
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.style.TextAlign
import com.example.tastebud.R
import com.example.tastebud.data.Instruction


data class Dish(val name: String, val cuisine: String, val imageUri: String)

@Composable
fun HomeScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    NavBarScaffold(navController, "Home") { HomeContent(navController, it, sharedViewModel) }
}


@OptIn(ExperimentalMaterial3Api::class)
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
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(200.dp),
            elevation = CardDefaults.cardElevation(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            onClick = {
                navController.navigate("CuisineSelectionScreen")
            },
            Row(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.chickentikka),
                    contentDescription = null,
                    modifier = Modifier.size(200.dp)

            Text(
                text = "RESUME YOUR JOURNEY",
                style = MaterialTheme.typography.headlineSmall.copy(color = Color(0xFFD88C45)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 8.dp),
                textAlign = TextAlign.Center
            )
        }
        Text(
            text = "OUR WEEKLY PICKS:",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, top = 8.dp),
            textAlign = TextAlign.Center
        )


        dishes.forEachIndexed() { index, dish ->
            Card(
                onClick = {
                    CreateRecipe (sharedViewModel)
                    navController.navigate("recipeDetailsScreen")

                }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically, // Center vertically within the row
                    horizontalArrangement = Arrangement.Start, // Align content to the start horizontally
                    modifier = Modifier.fillMaxWidth(), // Expand the row to fill the width

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
                        modifier = Modifier.size(imageSize),
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center
                    )
                }
            }
        }
    }
}

fun CreateRecipe(sharedViewModel: SharedViewModel){
    val ingredientListPaniPuri = listOf(
        Ingredient("1", "Semolina (Sooji)", "1 cup of semolina (sooji)", "", "1 cup", "cup"),
        Ingredient("2", "All-purpose flour", "1/2 cup of all-purpose flour", "", "1/2 cup", "cup"),
        Ingredient("3", "Baking soda", "1/4 teaspoon of baking soda", "", "1/4 teaspoon", "teaspoon"),
        Ingredient("4", "Salt", "1/2 teaspoon of salt", "", "1/2 teaspoon", "teaspoon"),
        Ingredient("5", "Water", "1/2 cup of water (adjust as needed)", "", "1/2 cup", "cup"),
        Ingredient("6", "Oil", "For deep frying", "", "As needed", ""),
        Ingredient("7", "Pani Puri Filling", "Prepared filling such as boiled chickpeas, mashed potatoes, chopped onions, tamarind chutney, spicy water, etc.", "", "As needed", "")
    )

    val stepsPaniPuri = listOf(
        Instruction(1, "In a mixing bowl, combine semolina, all-purpose flour, baking soda, and salt.", listOf(
            Ingredient("1", "Semolina (Sooji)", "1 cup of semolina (sooji)", "", "1 cup", "cup"),
            Ingredient("2", "All-purpose flour", "1/2 cup of all-purpose flour", "", "1/2 cup", "cup"),
            Ingredient("3", "Baking soda", "1/4 teaspoon of baking soda", "", "1/4 teaspoon", "teaspoon"),
            Ingredient("4", "Salt", "1/2 teaspoon of salt", "", "1/2 teaspoon", "teaspoon")
        ), listOf(
            Equipment("1", "Mixing bowl", "")
        )),
        Instruction(2, "Gradually add water to the dry ingredients and knead into a smooth dough. Cover the dough and let it rest for 15-20 minutes.", listOf(
            Ingredient("5", "Water", "1/2 cup of water (adjust as needed)", "", "1/2 cup", "cup")
        ), listOf(
            Equipment("2", "Cover", "")
        )),
        Instruction(3, "Divide the dough into small balls. Roll out each ball into a thin circle (puri) using a rolling pin.", listOf(), listOf(
            Equipment("3", "Rolling pin", "")
        )),
        Instruction(4, "Heat oil in a deep frying pan. Fry the rolled out puris until they puff up and turn golden brown. Remove and drain excess oil on paper towels.", listOf(
            Ingredient("6", "Oil", "For deep frying", "", "As needed", "")
        ), listOf(
            Equipment("4", "Deep frying pan", ""),
            Equipment("5", "Paper towels", "")
        ))
    )

    val recipePaniPuri = Recipe(
        "2",
        "Pani Puri",
        "https://www.example.com/panipuri.jpg",
        "30 min",
        4,
        listOf("Indian", "Street Food"),
        true,
        false,
        false,
        false,
        true,
        true,
        "Easy",
        ingredientListPaniPuri,
        stepsPaniPuri
    )

    val ingredientListGnocchi = listOf(
        Ingredient("1", "Potatoes", "2 large potatoes (about 1 kg)", "", "2 large potatoes", ""),
        Ingredient("2", "All-purpose flour", "1 1/2 cups of all-purpose flour (plus more for dusting)", "", "1 1/2 cups", "cup"),
        Ingredient("3", "Salt", "1 teaspoon of salt", "", "1 teaspoon", "teaspoon"),
        Ingredient("4", "Egg", "1 large egg", "", "1", ""),
        Ingredient("5", "Parmesan cheese", "1/4 cup of grated Parmesan cheese", "", "1/4 cup", "cup")
    )

    val stepsGnocchi = listOf(
        Instruction(1, "Boil the potatoes in their skins until fork-tender, about 30-40 minutes. Drain and let them cool slightly.", listOf(
            Ingredient("1", "Potatoes", "2 large potatoes (about 1 kg)", "", "2 large potatoes", "")
        ), listOf(
            Equipment("1", "Pot", ""),
            Equipment("2", "Colander", "")
        )),
        Instruction(2, "Peel the potatoes while they are still warm, then mash them or pass them through a potato ricer onto a clean surface.", listOf(), listOf(
            Equipment("3", "Potato masher or ricer", "")
        )),
        Instruction(3, "In a large mixing bowl, combine the mashed potatoes with the flour, salt, egg, and Parmesan cheese. Mix until a dough forms.", listOf(
            Ingredient("2", "All-purpose flour", "1 1/2 cups of all-purpose flour (plus more for dusting)", "", "1 1/2 cups", "cup"),
            Ingredient("3", "Salt", "1 teaspoon of salt", "", "1 teaspoon", "teaspoon"),
            Ingredient("4", "Egg", "1 large egg", "", "1", ""),
            Ingredient("5", "Parmesan cheese", "1/4 cup of grated Parmesan cheese", "", "1/4 cup", "cup")
        ), listOf(
            Equipment("4", "Mixing bowl", "")
        )),
        Instruction(4, "Knead the dough gently until smooth. Divide it into smaller portions.", listOf(), listOf()),
        Instruction(5, "On a floured surface, roll each portion into a long rope about 1/2 inch thick. Cut the rope into bite-sized pieces.", listOf(), listOf(
            Equipment("5", "Floured surface", ""),
            Equipment("6", "Knife", "")
        )),
        Instruction(6, "Using a fork or gnocchi board, make ridges on each gnocchi. This helps to hold the sauce later on.", listOf(), listOf(
            Equipment("7", "Fork or gnocchi board", "")
        )),
        Instruction(7, "Bring a large pot of salted water to a boil. Cook the gnocchi in batches, being careful not to overcrowd the pot. They are ready when they float to the surface, usually about 2-3 minutes.", listOf(), listOf(
            Equipment("8", "Large pot", "")
        )),
        Instruction(8, "Remove the cooked gnocchi with a slotted spoon and transfer them to a serving dish. Serve with your favorite sauce and additional Parmesan cheese, if desired.", listOf(), listOf(
            Equipment("9", "Slotted spoon", ""),
            Equipment("10", "Serving dish", "")
        ))
    )

    val recipeGnocchi = Recipe(
        "3",
        "Potato Gnocchi",
        "https://www.example.com/gnocchi.jpg",
        "60 min",
        4,
        listOf("Italian", "Pasta"),
        true,
        false,
        false,
        false,
        true,
        false,
        "Intermediate",
        ingredientListGnocchi,
        stepsGnocchi
    )

    val ingredientListChowMein = listOf(
        Ingredient("1", "Chow Mein Noodles", "200g of chow mein noodles", "", "200g", "grams"),
        Ingredient("2", "Chicken Breast", "2 chicken breasts, thinly sliced", "", "2", ""),
        Ingredient("3", "Vegetables", "2 cups of mixed vegetables (such as bell peppers, carrots, and cabbage), thinly sliced", "", "2 cups", "cups"),
        Ingredient("4", "Soy Sauce", "3 tablespoons of soy sauce", "", "3 tablespoons", "tablespoons"),
        Ingredient("5", "Oyster Sauce", "2 tablespoons of oyster sauce", "", "2 tablespoons", "tablespoons"),
        Ingredient("6", "Garlic", "2 cloves of garlic, minced", "", "2 cloves", ""),
        Ingredient("7", "Ginger", "1-inch piece of ginger, grated", "", "1-inch piece", ""),
        Ingredient("8", "Sesame Oil", "1 tablespoon of sesame oil", "", "1 tablespoon", "tablespoon"),
        Ingredient("9", "Vegetable Oil", "2 tablespoons of vegetable oil", "", "2 tablespoons", "tablespoons"),
        Ingredient("10", "Salt", "To taste", "", "To taste", ""),
        Ingredient("11", "Black Pepper", "To taste", "", "To taste", "")
    )

    val stepsChowMein = listOf(
        Instruction(1, "Cook the chow mein noodles according to package instructions. Drain and set aside.", listOf(
            Ingredient("1", "Chow Mein Noodles", "200g of chow mein noodles", "", "200g", "grams")
        ), listOf(
            Equipment("1", "Pot", ""),
            Equipment("2", "Colander", "")
        )),
        Instruction(2, "Heat vegetable oil in a large skillet or wok over medium-high heat. Add minced garlic and grated ginger, sauté for 1 minute until fragrant.", listOf(
            Ingredient("6", "Garlic", "2 cloves of garlic, minced", "", "2 cloves", ""),
            Ingredient("7", "Ginger", "1-inch piece of ginger, grated", "", "1-inch piece", ""),
            Ingredient("9", "Vegetable Oil", "2 tablespoons of vegetable oil", "", "2 tablespoons", "tablespoons")
        ), listOf(
            Equipment("3", "Skillet or wok", "")
        )),
        Instruction(3, "Add sliced chicken breasts to the skillet. Cook until they are no longer pink, about 5-6 minutes.", listOf(
            Ingredient("2", "Chicken Breast", "2 chicken breasts, thinly sliced", "", "2", "")
        ), listOf()),
        Instruction(4, "Add the mixed vegetables to the skillet. Stir-fry for 2-3 minutes until they are tender yet crisp.", listOf(
            Ingredient("3", "Vegetables", "2 cups of mixed vegetables (such as bell peppers, carrots, and cabbage), thinly sliced", "", "2 cups", "cups")
        ), listOf()),
        Instruction(5, "Add soy sauce, oyster sauce, sesame oil, salt, and black pepper. Mix well to combine.", listOf(
            Ingredient("4", "Soy Sauce", "3 tablespoons of soy sauce", "", "3 tablespoons", "tablespoons"),
            Ingredient("5", "Oyster Sauce", "2 tablespoons of oyster sauce", "", "2 tablespoons", "tablespoons"),
            Ingredient("8", "Sesame Oil", "1 tablespoon of sesame oil", "", "1 tablespoon", "tablespoon"),
            Ingredient("10", "Salt", "To taste", "", "To taste", ""),
            Ingredient("11", "Black Pepper", "To taste", "", "To taste", "")
        ), listOf()),
        Instruction(6, "Add the cooked chow mein noodles to the skillet. Toss everything together until the noodles are well coated with the sauce and heated through.", listOf(), listOf()),
        Instruction(7, "Serve hot and enjoy your delicious homemade chow mein!", listOf(), listOf())
    )

    val recipeChowMein = Recipe(
        "4",
        "Chicken Chow Mein",
        "https://www.example.com/chowmein.jpg",
        "30 min",
        4,
        listOf("Chinese", "Noodles"),
        true,
        false,
        false,
        true,
        false,
        false,
        "Easy",
        ingredientListChowMein,
        stepsChowMein
    )

    val ingredientListBaklava = listOf(
        Ingredient("1", "Phyllo Dough", "1 package of phyllo dough, thawed", "", "1 package", ""),
        Ingredient("2", "Walnuts", "2 cups of walnuts, finely chopped", "", "2 cups", "cups"),
        Ingredient("3", "Sugar", "1 cup of sugar", "", "1 cup", "cup"),
        Ingredient("4", "Cinnamon", "1 teaspoon of ground cinnamon", "", "1 teaspoon", "teaspoon"),
        Ingredient("5", "Butter", "1 cup of melted butter", "", "1 cup", "cup"),
        Ingredient("6", "Water", "1/2 cup of water", "", "1/2 cup", "cup"),
        Ingredient("7", "Honey", "1/2 cup of honey", "", "1/2 cup", "cup"),
        Ingredient("8", "Vanilla Extract", "1 teaspoon of vanilla extract", "", "1 teaspoon", "teaspoon"),
        Ingredient("9", "Lemon Juice", "1 tablespoon of lemon juice", "", "1 tablespoon", "tablespoon")
    )

    val stepsBaklava = listOf(
        Instruction(1, "Preheat your oven to 350°F (175°C).", listOf(), listOf(
            Equipment("1", "Oven", "")
        )),
        Instruction(2, "In a mixing bowl, combine finely chopped walnuts, sugar, and ground cinnamon.", listOf(
            Ingredient("2", "Walnuts", "2 cups of walnuts, finely chopped", "", "2 cups", "cups"),
            Ingredient("3", "Sugar", "1 cup of sugar", "", "1 cup", "cup"),
            Ingredient("4", "Cinnamon", "1 teaspoon of ground cinnamon", "", "1 teaspoon", "teaspoon")
        ), listOf(
            Equipment("2", "Mixing bowl", "")
        )),
        Instruction(3, "Brush the bottom of a baking dish with melted butter. Layer 2 sheets of phyllo dough in the dish, brushing each sheet with melted butter.", listOf(
            Ingredient("1", "Phyllo Dough", "1 package of phyllo dough, thawed", "", "1 package", ""),
            Ingredient("5", "Butter", "1 cup of melted butter", "", "1 cup", "cup")
        ), listOf(
            Equipment("3", "Baking dish", ""),
            Equipment("4", "Pastry brush", "")
        )),
        Instruction(4, "Sprinkle a layer of the walnut mixture over the phyllo dough. Repeat the layering process until all the ingredients are used, finishing with a top layer of phyllo dough brushed with melted butter.", listOf(), listOf()),
        Instruction(5, "Using a sharp knife, cut the baklava into diamond or square shapes.", listOf(), listOf(
            Equipment("5", "Sharp knife", "")
        )),
        Instruction(6, "Bake in the preheated oven for 45-50 minutes, or until the baklava is golden brown and crispy.", listOf(), listOf()),
        Instruction(7, "While the baklava is baking, prepare the syrup. In a saucepan, combine water, honey, sugar, lemon juice, and vanilla extract. Bring to a boil, then reduce the heat and simmer for 10-15 minutes until slightly thickened.", listOf(
            Ingredient("6", "Water", "1/2 cup of water", "", "1/2 cup", "cup"),
            Ingredient("7", "Honey", "1/2 cup of honey", "", "1/2 cup", "cup"),
            Ingredient("3", "Sugar", "1 cup of sugar", "", "1 cup", "cup"),
            Ingredient("9", "Lemon Juice", "1 tablespoon of lemon juice", "", "1 tablespoon", "tablespoon"),
            Ingredient("8", "Vanilla Extract", "1 teaspoon of vanilla extract", "", "1 teaspoon", "teaspoon")
        ), listOf(
            Equipment("6", "Saucepan", "")
        )),
        Instruction(8, "Once the baklava is out of the oven, immediately pour the hot syrup over the hot baklava. Let it cool completely before serving to allow the flavors to meld together.", listOf(), listOf())
    )

    val recipeBaklava = Recipe(
        "5",
        "Baklava",
        "https://www.example.com/baklava.jpg",
        "60 min",
        6,
        listOf("Middle Eastern", "Dessert"),
        true,
        false,
        false,
        true,
        false,
        false,
        "Intermediate",
        ingredientListBaklava,
        stepsBaklava
    )

    sharedViewModel.addRecipe(recipePaniPuri)
    sharedViewModel.addRecipe(recipeGnocchi)
    sharedViewModel.addRecipe(recipeChowMein)
    sharedViewModel.addRecipe(recipeBaklava)

}


