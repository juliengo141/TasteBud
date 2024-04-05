package com.example.tastebud.screens.home

import NavBarScaffold
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.AlertDialogDefaults.shape
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tastebud.R
import com.example.tastebud.data.Instruction
import com.example.tastebud.ui.theme.Inter
import com.example.tastebud.ui.theme.TasteBudBackground
import com.example.tastebud.ui.theme.TasteBudCard
import com.example.tastebud.ui.theme.TasteBudGreen
import com.example.tastebud.ui.theme.TasteBudOrange


data class Dish(val name: String, val cuisine: String, val imageUri: String)

@Composable
fun HomeScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    NavBarScaffold(navController, "Home") { HomeContent(navController, it, sharedViewModel) }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(navController: NavController, innerPadding: PaddingValues, sharedViewModel: SharedViewModel) {

    var searchText by remember { mutableStateOf("") }

    val dishes = listOf(
        recipePaniPuri,
        recipeGnocchi,
        recipeChowMein,
        recipeBaklava
    )

    val imageSize = 75.dp // Set a fixed size for the images
    val horizontalSpacing = 4.dp

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(innerPadding),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        // Search Bar
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                label = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = TasteBudGreen // Set the tint color to green
                        )
                        ClickableText(
                            text = AnnotatedString("Search for recipes..."),
                            onClick = {
                                navController.navigate("RecipeDetailsScreen")
                            },
                            modifier = Modifier
                                .padding(start = 8.dp, end = 8.dp) // Adjust the padding here
                                .weight(1f), // Take up remaining horizontal space
                            //shape = RoundedCornerShape(16.dp) // Adjust the corner radius as needed
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontFamily = Inter,
                                fontWeight = FontWeight.Bold
                            ),
                        )
                    }
                },
                modifier = Modifier
                    .wrapContentWidth() // Wrap content width to center horizontally
                    .padding(horizontal = 16.dp), // Add horizontal padding
            )
        }

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

                val filteredDishes = if (searchText.isNotBlank()) {
                    dishes.filter { it.title.contains(searchText, ignoreCase = true) }
                } else {
                    dishes
                }


                dishes.forEachIndexed() { index, dish ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = TasteBudCard),
                        elevation = CardDefaults.cardElevation(10.dp),
                        onClick = {
                            sharedViewModel.addRecipe(dish)
                            navController.navigate("recipeDetailsScreen")

                        },
                        modifier = Modifier
                            .fillMaxWidth() // Adjust the value as needed to make the cards narrower
                            .padding(10.dp, 6.dp),
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically, // Center vertically within the row
                            horizontalArrangement = Arrangement.Start, // Align content to the start horizontally
                            modifier = Modifier.fillMaxWidth(), // Expand the row to fill the width

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
                                verticalArrangement = Arrangement.Center, // Center vertically within the column
                                horizontalAlignment = Alignment.CenterHorizontally // Center horizontally within the column
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

    val ingredientListPaniPuri = listOf(
        Ingredient("1", "Semolina (Sooji)", "1 cup of semolina (sooji)", "", "1 cup", "cup"),
        Ingredient("2", "All-purpose flour", "1/2 cup of all-purpose flour", "", "1/2 cup", "cup"),
        Ingredient("3", "Baking soda", "1/4 teaspoon of baking soda", "", "1/4 teaspoon", "teaspoon"),
        Ingredient("4", "Salt", "1/2 teaspoon of salt", "", "1/2 teaspoon", "teaspoon"),
        Ingredient("5", "Water", "1/2 cup of water (adjust as needed)", "", "1/2 cup", "cup"),
        Ingredient("6", "Oil", "For deep frying", "", "As needed", ""),
        Ingredient(
            "7",
            "Pani Puri Filling",
            "Prepared filling such as boiled chickpeas, mashed potatoes, chopped onions, tamarind chutney, spicy water, etc.",
            "",
            "As needed",
            ""
        )
    )

    val stepsPaniPuri = listOf(
        Instruction(
            1, "In a mixing bowl, combine semolina, all-purpose flour, baking soda, and salt.", listOf(
                Equipment("1", "Semolina (Sooji)", "1 cup of semolina (sooji)"),
                Equipment("2", "All-purpose flour", "1/2 cup of all-purpose flour"),
                Equipment("3", "Baking soda", "1/4 teaspoon of baking soda"),
                Equipment("4", "Salt", "1/2 teaspoon of salt")
            ), listOf(
                Equipment("1", "Mixing bowl", "")
            )
        ),
        Instruction(
            2,
            "Gradually add water to the dry ingredients and knead into a smooth dough. Cover the dough and let it rest for 15-20 minutes.",
            listOf(
                Equipment("5", "Water", "1/2 cup of water (adjust as needed)")
            ),
            listOf(
                Equipment("2", "Cover", "")
            )
        ),
        Instruction(
            3,
            "Divide the dough into small balls. Roll out each ball into a thin circle (puri) using a rolling pin.",
            listOf(),
            listOf(
                Equipment("3", "Rolling pin", "")
            )
        ),
        Instruction(
            4,
            "Heat oil in a deep frying pan. Fry the rolled out puris until they puff up and turn golden brown. Remove and drain excess oil on paper towels.",
            listOf(
                Equipment("6", "Oil", "For deep frying")
            ),
            listOf(
                Equipment("4", "Deep frying pan", ""),
                Equipment("5", "Paper towels", "")
            )
        )
    )

    val recipePaniPuri = Recipe(
        "2",
        "Pani Puri",
        "https://www.sidechef.com/recipe/3883dffb-5fa2-4ee9-8054-d8de1409899f.jpg",
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
        Ingredient(
            "2",
            "All-purpose flour",
            "1 1/2 cups of all-purpose flour (plus more for dusting)",
            "",
            "1 1/2 cups",
            "cup"
        ),
        Ingredient("3", "Salt", "1 teaspoon of salt", "", "1 teaspoon", "teaspoon"),
        Ingredient("4", "Egg", "1 large egg", "", "1", ""),
        Ingredient("5", "Parmesan cheese", "1/4 cup of grated Parmesan cheese", "", "1/4 cup", "cup")
    )

    val stepsGnocchi = listOf(
        Instruction(
            1,
            "Boil the potatoes in their skins until fork-tender, about 30-40 minutes. Drain and let them cool slightly.",
            listOf(
                Equipment("1", "Potatoes", "2 large potatoes (about 1 kg)")
            ),
            listOf(
                Equipment("1", "Pot", ""),
                Equipment("2", "Colander", "")
            )
        ),
        Instruction(
            2,
            "Peel the potatoes while they are still warm, then mash them or pass them through a potato ricer onto a clean surface.",
            listOf(),
            listOf(
                Equipment("3", "Potato masher or ricer", "")
            )
        ),
        Instruction(
            3,
            "In a large mixing bowl, combine the mashed potatoes with the flour, salt, egg, and Parmesan cheese. Mix until a dough forms.",
            listOf(
                Equipment("2", "All-purpose flour", "1 1/2 cups of all-purpose flour (plus more for dusting)"),
                Equipment("3", "Salt", "1 teaspoon of salt"),
                Equipment("4", "Egg", "1 large egg"),
                Equipment("5", "Parmesan cheese", "1/4 cup of grated Parmesan cheese")
            ),
            listOf(
                Equipment("4", "Mixing bowl", "")
            )
        ),
        Instruction(4, "Knead the dough gently until smooth. Divide it into smaller portions.", listOf(), listOf()),
        Instruction(
            5,
            "On a floured surface, roll each portion into a long rope about 1/2 inch thick. Cut the rope into bite-sized pieces.",
            listOf(),
            listOf(
                Equipment("5", "Floured surface", ""),
                Equipment("6", "Knife", "")
            )
        ),
        Instruction(
            6,
            "Using a fork or gnocchi board, make ridges on each gnocchi. This helps to hold the sauce later on.",
            listOf(),
            listOf(
                Equipment("7", "Fork or gnocchi board", "")
            )
        ),
        Instruction(
            7,
            "Bring a large pot of salted water to a boil. Cook the gnocchi in batches, being careful not to overcrowd the pot. They are ready when they float to the surface, usually about 2-3 minutes.",
            listOf(),
            listOf(
                Equipment("8", "Large pot", "")
            )
        ),
        Instruction(
            8,
            "Remove the cooked gnocchi with a slotted spoon and transfer them to a serving dish. Serve with your favorite sauce and additional Parmesan cheese, if desired.",
            listOf(),
            listOf(
                Equipment("9", "Slotted spoon", ""),
                Equipment("10", "Serving dish", "")
            )
        )
    )

    val recipeGnocchi = Recipe(
        "3",
        "Potato Gnocchi",
        "https://www.allrecipes.com/thmb/0i_eJYJTanc0360TUBEt1yjehHU=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/8229095-500d92fad2f94a478636ec0433137077.jpg",
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
        Ingredient(
            "3",
            "Vegetables",
            "2 cups of mixed vegetables (such as bell peppers, carrots, and cabbage), thinly sliced",
            "",
            "2 cups",
            "cups"
        ),
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
        Instruction(
            1, "Cook the chow mein noodles according to package instructions. Drain and set aside.", listOf(
                Equipment("1", "Chow Mein Noodles", "200g of chow mein noodles")
            ), listOf(
                Equipment("1", "Pot", ""),
                Equipment("2", "Colander", "")
            )
        ),
        Instruction(
            2,
            "Heat vegetable oil in a large skillet or wok over medium-high heat. Add minced garlic and grated ginger, sauté for 1 minute until fragrant.",
            listOf(
                Equipment("6", "Garlic", "2 cloves of garlic, minced"),
                Equipment("7", "Ginger", "1-inch piece of ginger, grated"),
                Equipment("9", "Vegetable Oil", "2 tablespoons of vegetable oil")
            ),
            listOf(
                Equipment("3", "Skillet or wok", "")
            )
        ),
        Instruction(
            3,
            "Add sliced chicken breasts to the skillet. Cook until they are no longer pink, about 5-6 minutes.",
            listOf(
                Equipment("2", "Chicken Breast", "2 chicken breasts, thinly sliced")
            ),
            listOf()
        ),
        Instruction(
            4,
            "Add the mixed vegetables to the skillet. Stir-fry for 2-3 minutes until they are tender yet crisp.",
            listOf(
                Equipment(
                    "3",
                    "Vegetables",
                    "2 cups of mixed vegetables (such as bell peppers, carrots, and cabbage), thinly sliced"
                )
            ),
            listOf()
        ),
        Instruction(
            5, "Add soy sauce, oyster sauce, sesame oil, salt, and black pepper. Mix well to combine.", listOf(
                Equipment("4", "Soy Sauce", "3 tablespoons of soy sauce"),
                Equipment("5", "Oyster Sauce", "2 tablespoons of oyster sauce"),
                Equipment("8", "Sesame Oil", "1 tablespoon of sesame oil"),
                Equipment("10", "Salt", "To taste"),
                Equipment("11", "Black Pepper", "To taste")
            ), listOf()
        ),
        Instruction(
            6,
            "Add the cooked chow mein noodles to the skillet. Toss everything together until the noodles are well coated with the sauce and heated through.",
            listOf(),
            listOf()
        ),
        Instruction(7, "Serve hot and enjoy your delicious homemade chow mein!", listOf(), listOf())
    )

    val recipeChowMein = Recipe(
        "4",
        "Chicken Chow Mein",
        "https://mymorningmocha.com/wp-content/uploads/2023/07/special-chow-mein-recipe.jpg",
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
        Instruction(
            1, "Preheat your oven to 350°F (175°C).", listOf(), listOf(
                Equipment("1", "Oven", "")
            )
        ),
        Instruction(
            2, "In a mixing bowl, combine finely chopped walnuts, sugar, and ground cinnamon.", listOf(
                Equipment("2", "Walnuts", "2 cups of walnuts, finely chopped"),
                Equipment("3", "Sugar", "1 cup of sugar"),
                Equipment("4", "Cinnamon", "1 teaspoon of ground cinnamon")
            ), listOf(
                Equipment("2", "Mixing bowl", "")
            )
        ),
        Instruction(
            3,
            "Brush the bottom of a baking dish with melted butter. Layer 2 sheets of phyllo dough in the dish, brushing each sheet with melted butter.",
            listOf(
                Equipment("1", "Phyllo Dough", "1 package of phyllo dough, thawed"),
                Equipment("5", "Butter", "1 cup of melted butter")
            ),
            listOf(
                Equipment("3", "Baking dish", ""),
                Equipment("4", "Pastry brush", "")
            )
        ),
        Instruction(
            4,
            "Sprinkle a layer of the walnut mixture over the phyllo dough. Repeat the layering process until all the ingredients are used, finishing with a top layer of phyllo dough brushed with melted butter.",
            listOf(),
            listOf()
        ),
        Instruction(
            5, "Using a sharp knife, cut the baklava into diamond or square shapes.", listOf(), listOf(
                Equipment("5", "Sharp knife", "")
            )
        ),
        Instruction(
            6,
            "Bake in the preheated oven for 45-50 minutes, or until the baklava is golden brown and crispy.",
            listOf(),
            listOf()
        ),
        Instruction(
            7,
            "While the baklava is baking, prepare the syrup. In a saucepan, combine water, honey, sugar, lemon juice, and vanilla extract. Bring to a boil, then reduce the heat and simmer for 10-15 minutes until slightly thickened.",
            listOf(
                Equipment("6", "Water", "1/2 cup of water"),
                Equipment("7", "Honey", "1/2 cup of honey"),
                Equipment("3", "Sugar", "1 cup of sugar"),
                Equipment("9", "Lemon Juice", "1 tablespoon of lemon juice"),
                Equipment("8", "Vanilla Extract", "1 teaspoon of vanilla extract")
            ),
            listOf(
                Equipment("6", "Saucepan", "")
            )
        ),
        Instruction(
            8,
            "Once the baklava is out of the oven, immediately pour the hot syrup over the hot baklava. Let it cool completely before serving to allow the flavors to meld together.",
            listOf(),
            listOf()
        )
    )

    val recipeBaklava = Recipe(
        "5",
        "Baklava",
        "https://upload.wikimedia.org/wikipedia/commons/c/c7/Baklava%281%29.png",
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