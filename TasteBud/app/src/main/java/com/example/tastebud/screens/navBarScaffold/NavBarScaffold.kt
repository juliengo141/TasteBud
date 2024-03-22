import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Kitchen
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavBarScaffold(navController: NavController, createContent: @Composable (PaddingValues) -> Unit) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text("TasteBud")
        })
    }, bottomBar = {
        BottomAppBar(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.primary,
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp), // Adjust the height of the bottom bar as needed
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                // Define each button separately
                IconButton(
                    onClick = {
                        // Handle Home icon click
                        navController.navigate("homeScreen")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        modifier = Modifier.size(50.dp),
                        contentDescription = "Home"
                    )
                }



                IconButton(
                    onClick = {
                        // Handle Roulette icon click
                        navController.navigate("RandomRecipeScreen")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Casino,
                        modifier = Modifier.size(50.dp),
                        contentDescription = "Roulette"
                    )
                }
                IconButton(
                    onClick = {
                        // Handle Roulette icon click
                        navController.navigate("RecipeRoadScreen")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.MenuBook,
                        modifier = Modifier.size(50.dp),
                        contentDescription = "Journey"
                    )
                }

                IconButton(
                    onClick = {
                        // Handle Fridge icon click
                        navController.navigate("SelectIngredientScreen")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Kitchen,
                        modifier = Modifier.size(50.dp),
                        contentDescription = "Fridge"

                    )
                }
                IconButton(
                    onClick = {
                        // Handle Profile icon click
                        navController.navigate("profileScreen")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        modifier = Modifier.size(50.dp),
                        contentDescription = "Profile"
                    )
                }
        }



            // Add more buttons as needed
        }
    }) { innerPadding ->
        createContent(innerPadding)
    }
}