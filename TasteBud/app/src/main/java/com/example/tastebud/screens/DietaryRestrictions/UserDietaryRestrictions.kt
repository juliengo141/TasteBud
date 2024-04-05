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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tastebud.screens.SharedViewModel
import com.example.tastebud.ui.theme.Inter
import com.example.tastebud.ui.theme.TasteBudAccent
import com.example.tastebud.ui.theme.TasteBudBackground
import com.example.tastebud.ui.theme.TasteBudGreen

var fridgeIngredient = listOf<String>("Vegatarian", "Vegan", "Gluten-free", "Lactose-Intolerance", "Diabetes", "Dairy-free")
@Composable
fun UserDietaryRestrictionsScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    val selectedIngredients = remember { mutableStateListOf<String>() }
    NavBarScaffold(navController, "Dietary Restrictations") { selectContent1(navController, it, selectedIngredients) }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun selectContent1(navController: NavController, innerPadding: PaddingValues, selectedIngredients: MutableList<String>) {
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
        ){
            for(ingredient in fridgeIngredient){
                FilterChipExampl(
                    title = ingredient,
                    onIngredientSelected = { ingredientSelected ->
                        if (ingredientSelected) {
                            selectedIngredients.add(ingredient)
                        } else {
                            selectedIngredients.remove(ingredient)
                        }
                    }
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                Log.d("SelectedIngredients", selectedIngredients.toString())
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = TasteBudGreen
            ),
            modifier = Modifier
                .padding(16.dp)
                .height(40.dp)
        ) {
            Text(text = "Continue", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.White, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, fontFamily = Inter,)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterChipExampl(title: String, onIngredientSelected: (Boolean) -> Unit) {
    var selected by remember { mutableStateOf(false) }

    FilterChip(
        onClick = {
            selected = !selected
            onIngredientSelected(selected)
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

