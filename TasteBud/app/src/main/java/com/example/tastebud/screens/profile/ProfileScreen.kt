package com.example.tastebud.screens.home

import NavBarScaffold
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tastebud.R
import com.example.tastebud.data.*
import com.example.tastebud.screens.SharedViewModel
import com.example.tastebud.screens.recipeDetail.IngredientItem
import com.example.tastebud.ui.theme.TasteBudAccent
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject


@Composable
fun ProfileScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    NavBarScaffold(navController, "Profile") { ProfileContent(navController, it, sharedViewModel) }
}

@Composable
fun ProfileContent(navController: NavController, innerPadding: PaddingValues, sharedViewModel: SharedViewModel) {
    GetUserProfile(sharedViewModel = sharedViewModel)
    Column(
        modifier = Modifier.padding(0.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        sharedViewModel.user?.let {
            Column(
                modifier = Modifier.padding(innerPadding).padding(0.dp, 20.dp),
                verticalArrangement = Arrangement.spacedBy(28.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Profile Header
                ProfileHeader(sharedViewModel)

                // Icons Row
                IconsRow(sharedViewModel)

                // Cards Section
                CardsSection(sharedViewModel, navController)
            }
        }
    }
}

@Composable
fun GetUserProfile(sharedViewModel: SharedViewModel) {
    val documentId = sharedViewModel.userId?.uid
    Log.d("USERID", "${documentId}");
    var currUser: User

    val db = FirebaseFirestore.getInstance()
    val docRef = documentId?.let { db.collection("Users").document(it) }
    Log.d("DOCREF", "${docRef}");

    if (docRef != null) {
        docRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    currUser = User(
                        documentId,
                        (document.data?.get("email")).toString(),
                        (document.data?.get("fullName")).toString(),
                        (document.data?.get("dietaryRestrictions")) as List<String>,
                        (document.data?.get("minsCooked")) as Long,
                        (document.data?.get("completedCount")) as Long,
                        (document.data?.get("startedCount")) as Long,
                    )
                    Log.d("CURRUSERID", "currUser: ${currUser}");
//                    pickedRecipe = Recipe(
//                        (document.data?.get("id")).toString(),
//                        (document.data?.get("title")).toString(),
//                        (document.data?.get("image")).toString(),
//                        (document.data?.get("readyInMinutes")).toString() + " mins",
//                        (document.data?.get("servings")) as Long,
//                        (document.data?.get("cuisines")) as List<String>,
//                        (document.data?.get("vegetarian")) as Boolean,
//                        (document.data?.get("vegan")) as Boolean,
//                        (document.data?.get("glutenFree")) as Boolean,
//                        (document.data?.get("dairyFree")) as Boolean,
//                        (document.data?.get("veryHealthy")) as Boolean,
//                        (document.data?.get("cheap")) as Boolean,
//                        (document.data?.get("difficulty")).toString(),
//                        testIngredientList,
//                        steps
//                    )
                    sharedViewModel.addUser(currUser)
                } else {
                    Log.d("ERROR", "No such document");
                    println("No such document")
                }
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: $exception")
            }
    }
}

@Composable
fun ProfileHeader(sharedViewModel: SharedViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Profile Icon
        Icon(
            imageVector = Icons.Filled.AccountCircle,
            contentDescription = "Localized description",
            modifier = Modifier.size(50.dp)
        )

        // User's Name and Email (Random for now)
        Column {
            Text("${sharedViewModel.user?.fullName}", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
            Text("${sharedViewModel.user?.email}", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun IconsRow(sharedViewModel: SharedViewModel) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(40.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Dietary Restrictions", fontWeight = FontWeight.Bold)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // First row of circles
            CircleIcon(R.drawable.dairyfree, "Dairy Free")
            CircleIcon(R.drawable.vegan, "Vegan")
            CircleIcon(R.drawable.gluten, "Gluten Free")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // Second row of circles
            CircleIcon(R.drawable.vegatarian, "Vegetarian")
            CircleIcon(R.drawable.pescatarian, "Pescatarian")
            CircleIcon(R.drawable.keto, "Keto")
        }
    }
}

@Composable
fun CircleIcon(icon: Int, diet: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(TasteBudAccent),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "Circle Icon",
                modifier = Modifier.size(75.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = diet,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black, fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun CardsSection(sharedViewModel: SharedViewModel,navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        // First Card
        Card(
            modifier = Modifier.fillMaxWidth(0.5f).padding(12.dp),
            //elevation = 8.dp,
            //backgroundColor = Color.White,
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Recipes Completed",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Row(verticalAlignment = Alignment.CenterVertically){
                    Icon(
                        imageVector = Icons.Filled.CheckBox,
                        contentDescription = "Localized description",
                        modifier = Modifier.size(40.dp)
                    )
                    Text(
                        text = "${sharedViewModel.user?.completedCount} ",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Completed",
                        style = MaterialTheme.typography.bodyMedium
                    )

                }
            }
        }

        // Second Card
        Card(
            modifier = Modifier.fillMaxWidth(1f).padding(12.dp),
//             elevation = 8.dp,
//             backgroundColor = Color.White,
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Cooking Minutes",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Row(verticalAlignment = Alignment.CenterVertically){
                    Icon(
                        imageVector = Icons.Filled.Timer,
                        contentDescription = "Localized description",
                        modifier = Modifier.size(40.dp)
                    )
                    Text(
                        text = "${sharedViewModel.user?.minsCooked} ",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = " Minutes",
                        style = MaterialTheme.typography.bodyMedium
                    )

                }

            }
        }

    }
}