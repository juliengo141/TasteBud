package com.example.tastebud.screens.reciperoad

import NavBarScaffold
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.tastebud.data.Cuisine
import com.example.tastebud.screens.SharedViewModel

@Composable
fun CuisineSelectionScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    NavBarScaffold(navController, "Select Cuisine") { CuisineSelectionContent(navController, it, sharedViewModel) }
}

val IndianCuisine = Cuisine(
    title = "Indian",
    country = "India",
    image = "https://upload.wikimedia.org/wikipedia/en/thumb/4/41/Flag_of_India.svg/1200px-Flag_of_India.svg.png",
    disabled = false,
    disabledImage = "https://upload.wikimedia.org/wikipedia/commons/a/a4/India_flag_black_and_white.jpg"
)
val ItalianCuisine = Cuisine(
    title = "Italian",
    country = "Italy",
    image = "https://upload.wikimedia.org/wikipedia/en/thumb/0/03/Flag_of_Italy.svg/1200px-Flag_of_Italy.svg.png",
    disabled = false,
    disabledImage = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/Italy_flag_black_and_white.jpg/1024px-Italy_flag_black_and_white.jpg"
)
val ChineseCuisine = Cuisine(
    title = "Chinese",
    country = "China",
    image = "https://t3.ftcdn.net/jpg/05/09/75/06/360_F_509750646_dXGPtke91yl85iuv4hKUOIgH67e5iFCd.jpg",
    disabled = false,
    disabledImage = "https://www.shutterstock.com/image-vector/illustrated-grayscale-flag-country-china-260nw-226401670.jpg"
)
val AmericanCuisine = Cuisine(
    title = "American",
    country = "America",
    image = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a9/Flag_of_the_United_States_%28DoS_ECA_Color_Standard%29.svg/255px-Flag_of_the_United_States_%28DoS_ECA_Color_Standard%29.svg.png",
    disabled = false,
    disabledImage = "https://media.distractify.com/brand-img/WazSDFK3Z/0x0/black-us-american-flag-1618841105755.jpg"
)
val sampleCuisines = listOf(IndianCuisine, AmericanCuisine, ItalianCuisine, ChineseCuisine)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CuisineSelectionContent(
    navController: NavController, innerPadding: PaddingValues, sharedViewModel: SharedViewModel
) {
    Column(
        modifier = Modifier.padding(innerPadding)
    ) {
        Text(
            modifier = Modifier.padding(10.dp),
            text = "Choose Your Path",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )
        LazyColumn {
            items(sampleCuisines) { cuisine ->
                CuisineCard(cuisine = cuisine, navController, sharedViewModel)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CuisineCard(cuisine: Cuisine, navController: NavController, sharedViewModel: SharedViewModel) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp).height(200.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        enabled = !cuisine.disabled,
        onClick = {
            sharedViewModel.addCuisine(cuisine.title, cuisine.country)
            navController.navigate("RecipeRoadScreen")
        },
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = if (cuisine.disabled) cuisine.disabledImage else cuisine.image,
                contentDescription = "Translated description of what the image contains",
                modifier = Modifier.padding(15.dp, 0.dp).size(175.dp).align(Alignment.CenterVertically)
            )
            Column(
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(
                    text = cuisine.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
                if (cuisine.disabled) {
                    Text(
                        text = "LOCKED",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                }
            }
        }
    }
}