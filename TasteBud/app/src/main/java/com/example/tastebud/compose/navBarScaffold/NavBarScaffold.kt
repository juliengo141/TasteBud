import androidx.compose.foundation.layout.*
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp), // Adjust the height of the bottom bar as needed
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            // Define each button separately
            Button(
                modifier = Modifier.weight(1f), // Each button takes up equal space
                onClick = {
                    // Handle Home button click
                    navController.navigate("homeScreen")
                }
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = "Home"
                )
            }

            Button(
                modifier = Modifier.weight(1f), // Each button takes up equal space
                onClick = {
                    // Handle Profile button click
                    navController.navigate("profileScreen")
                }
            ) {
                Text(
                    textAlign = TextAlign.Right,
                    text = "Profile"
                )
            }


            Button(
                modifier = Modifier.weight(1f), // Each button takes up equal space
                onClick = {
                    // Handle Profile button click
                    navController.navigate("RandomRecipeScreen")
                }
            ) {
                Text(
                    textAlign = TextAlign.Left,
                    text = "Roulette"
                )
            }

            Button(
                modifier = Modifier.weight(1f), // Each button takes up equal space
                onClick = {
                    // Handle Profile button click
                    navController.navigate("SelectIngredientScreen")
                }
            ) {
                Text(
                    textAlign = TextAlign.Left,
                    text = "Fridge"
                )
            }

            // Add more buttons as needed
        }
    }) { innerPadding ->
        createContent(innerPadding)
    }
}