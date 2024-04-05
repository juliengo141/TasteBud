package com.example.tastebud.screens.home

import NavBarScaffold
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tastebud.R
import com.example.tastebud.data.User
import com.example.tastebud.screens.SharedViewModel
import com.example.tastebud.ui.theme.TasteBudAccent
import com.google.firebase.firestore.FirebaseFirestore


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
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(0.dp, 20.dp),
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
    Log.d("USERID", "${documentId}")
    var currUser: User

    val db = FirebaseFirestore.getInstance()
    val docRef = documentId?.let { db.collection("Users").document(it) }
    Log.d("DOCREF", "${docRef}")

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
                    Log.d("CURRUSERID", "currUser: ${currUser}")
                    sharedViewModel.addUser(currUser)
                } else {
                    Log.d("ERROR", "No such document")
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Profile Icon
        Icon(
            imageVector = Icons.Filled.AccountCircle,
            contentDescription = "Localized description",
            modifier = Modifier.size(50.dp)
        )

        // User's Name and Email
        Column {
            Text(
                "${sharedViewModel.user?.fullName}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                "${sharedViewModel.user?.email}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun IconsRow(sharedViewModel: SharedViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Dietary Restrictions", fontWeight = FontWeight.Bold)
        sharedViewModel.user?.let { dietaryRestrictionsRow(it.dietaryRestrictions) }
    }
}

@Composable
fun dietaryRestrictionsRow(dietaryList: List<String>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        // First row of circles
        CircleIcon(R.drawable.dairyfree, "Dairy Free", dietaryList)
        CircleIcon(R.drawable.vegan, "Vegan", dietaryList)
        CircleIcon(R.drawable.gluten, "Gluten Free", dietaryList)
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        // Second row of circles
        CircleIcon(R.drawable.vegatarian, "Vegetarian", dietaryList)
        CircleIcon(R.drawable.pescatarian, "Pescatarian", dietaryList)
        CircleIcon(R.drawable.keto, "Keto", dietaryList)
    }
}

@Composable
fun CircleIcon(icon: Int, diet: String, dietaryList: List<String>) {
    val backgroundColor = if (dietaryList.contains(diet)) TasteBudAccent else Color.Gray
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(backgroundColor),
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
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun CardsSection(sharedViewModel: SharedViewModel, navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(12.dp),
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
                Row(verticalAlignment = Alignment.CenterVertically) {
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
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(12.dp),
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

                Row(verticalAlignment = Alignment.CenterVertically) {
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