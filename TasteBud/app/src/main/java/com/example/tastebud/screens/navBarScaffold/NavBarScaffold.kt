import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Kitchen
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import androidx.compose.ui.unit.dp
import com.example.tastebud.R
import com.example.tastebud.ui.theme.TasteBudAccent
import com.example.tastebud.ui.theme.TasteBudDarkGreen
import com.example.tastebud.ui.theme.TasteBudGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavBarScaffold(navController: NavController, title: String, createContent: @Composable (PaddingValues) -> Unit) {
    Scaffold(topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = Color.White,
                titleContentColor = Color.Black,
            ),
            title = {
                Column {
                    Text(title)
                    Text(
                        text = "What will you cook today Chef?",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }

            },
            navigationIcon = {
                IconButton(onClick = { navController.navigate("HomeScreen") }, modifier = Modifier.size(75.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.tastebud),
                        contentDescription = "Localized description",
                        modifier = Modifier.size(250.dp)
                    )
                }
            },
            actions = {
                IconButton(onClick = { navController.navigate("ProfileScreen") }, modifier = Modifier.size(75.dp)) {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "Localized description",
                        modifier = Modifier.size(50.dp)
                    )
                }
            },
        )
    }, bottomBar = {
        BottomAppBar(
            containerColor = TasteBudAccent,
            contentColor = TasteBudDarkGreen,
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
                        navController.navigate("CuisineSelectionScreen")
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.MenuBook,
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