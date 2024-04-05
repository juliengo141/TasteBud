package com.example.tastebud.screens.home

import NavBarScaffold
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tastebud.components.GetUserProfile
import com.example.tastebud.screens.SharedViewModel
import com.example.tastebud.ui.theme.Inter
import com.example.tastebud.ui.theme.TasteBudAccent
import com.example.tastebud.ui.theme.TasteBudBackground
import com.example.tastebud.ui.theme.TasteBudGreen
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

var availableDiets = listOf<String>("Vegetarian", "Vegan", "Gluten Free", "Dairy Free", "Pescatarian", "Keto")

@Composable
fun UserDietaryRestrictionsScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    GetUserProfile(sharedViewModel)
    val selectedIngredients = remember { mutableStateListOf<String>() }
    NavBarScaffold(navController, "Dietary Restrictions") {
        SelectDiet(
            navController,
            it,
            selectedIngredients,
            sharedViewModel
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SelectDiet(
    navController: NavController,
    innerPadding: PaddingValues,
    selectedDiets: MutableList<String>,
    sharedViewModel: SharedViewModel
) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .padding(20.dp, 16.dp)
            .background(TasteBudBackground),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = """
                    Dietary Restrictions
                """.trimIndent(),
            fontWeight = FontWeight.Black,
            fontFamily = Inter,
            fontSize = 48.sp,
            lineHeight = 1.3.em,
        )
        Text(
            modifier = Modifier.padding(8.dp),
            text = """
                    Let us know about your dietary restrictions.
                """.trimIndent(),
            fontFamily = Inter,
        )

        FlowRow(
            modifier = Modifier
                .padding(8.dp, 0.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            for (diets in availableDiets) {
                FilterChipExampl(
                    title = diets,
                    onDietSelected = { dietSelected ->
                        if (dietSelected) {
                            selectedDiets.add(diets)
                        } else {
                            selectedDiets.remove(diets)
                        }
                    }
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                Log.d("DIET", selectedDiets.toString())
                val db = Firebase.firestore
                val updatedDiets = hashMapOf("dietaryRestrictions" to selectedDiets)
                sharedViewModel.user?.let {
                    db.collection("Users").document(it.userId)
                        .set(updatedDiets, SetOptions.merge())
                }
                navController.navigate("homeScreen")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = TasteBudGreen
            ),
            modifier = Modifier
                .padding(16.dp)
                .height(40.dp)
        ) {
            Text(
                text = "Continue",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = Inter,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterChipExampl(title: String, onDietSelected: (Boolean) -> Unit) {
    var selected by remember { mutableStateOf(false) }

    FilterChip(
        onClick = {
            selected = !selected
            onDietSelected(selected)
        },
        label = {
            Text(text = title, modifier = Modifier.padding(vertical = 8.dp, horizontal = 10.dp))
        },
        selected = selected,
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = TasteBudAccent
        ),

        leadingIcon = if (selected) {
            {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Done icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else {
            null
        },
    )
}

