package com.example.tastebud.screens.recipeDetail

import NavBarScaffold
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tastebud.data.Instruction
import com.example.tastebud.screens.SharedViewModel
import com.example.tastebud.ui.theme.TasteBudGreen
import com.example.tastebud.ui.theme.TasteBudOrange
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

@Composable
fun FlashcardsScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    NavBarScaffold(navController, "Recipe Book") { FlashcardContent(navController, it, sharedViewModel) }
}

@Composable
fun FlashcardContent(navController: NavController, innerPadding: PaddingValues, sharedViewModel: SharedViewModel) {
    Column(
        modifier = Modifier.padding(innerPadding),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
        ) {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black // Specify the color of the icon here
            )
        }
        Text(
            "Instructions", modifier = Modifier.padding(15.dp, 0.dp), fontSize = 20.sp, fontWeight = FontWeight.Bold
        )
        if (sharedViewModel.recipe != null) {
            val steps = sharedViewModel.recipe?.steps
            if (steps != null) {
                RecipeStepsPager(steps = steps, sharedViewModel, navController)
            }
        }
    }
}

@Composable
fun RecipeStepsPager(steps: List<Instruction>, sharedViewModel: SharedViewModel, navController: NavController) {
    val pageCount = steps.size
    val pagerState = rememberPagerState(pageCount = { pageCount })
    val indicatorScrollState = rememberLazyListState()

    // Scroll the indicator when the current page changes
    LaunchedEffect(key1 = pagerState.currentPage) {
        val currentPage = pagerState.currentPage
        val size = indicatorScrollState.layoutInfo.visibleItemsInfo.size
        val lastVisibleIndex = indicatorScrollState.layoutInfo.visibleItemsInfo.last().index
        val firstVisibleItemIndex = indicatorScrollState.firstVisibleItemIndex

        if (currentPage > lastVisibleIndex - 1) {
            indicatorScrollState.animateScrollToItem(currentPage - size + 2)
        } else if (currentPage <= firstVisibleItemIndex + 1) {
            indicatorScrollState.animateScrollToItem(maxOf(currentPage - 1, 0))
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)
    ) {
        // Horizontal pager for recipe steps
        HorizontalPager(
            state = pagerState, modifier = Modifier.fillMaxSize().weight(3f)

        ) { page ->
            Box(
                modifier = Modifier.fillMaxSize().padding(8.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(16.dp))
            ) {
                Text(
                    text = "${steps[page].stepNumber}: ${steps[page].step}",
                    lineHeight = 30.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(20.dp)
                )
            }
        }

        // Indicator dots
        LazyRow(
            modifier = Modifier.fillMaxWidth().weight(2f),
            horizontalArrangement = Arrangement.Center,
            state = indicatorScrollState
        ) {
            items(steps) { step ->
                val stepNumber = step.stepNumber
                val color = if (pagerState.currentPage == stepNumber.toInt() - 1) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier.padding(8.dp).background(color, CircleShape).size(10.dp)
                )
            }
        }

        // Done Button
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (!pagerState.isScrollInProgress && pagerState.currentPage == pageCount - 1) {
                Button(
                    onClick = {
                        val db = Firebase.firestore
                        val additionalMin = sharedViewModel.recipe?.estimatedMins
                        val updatedCompletedCount =
                            hashMapOf("completedCount" to (sharedViewModel.user?.completedCount ?: 0) + 1)
                        val incrementMinsCooked =
                            hashMapOf("minsCooked" to (sharedViewModel.user?.minsCooked ?: 0) + additionalMin!!)
                        sharedViewModel.user?.let {
                            db.collection("Users").document(it.userId).set(updatedCompletedCount, SetOptions.merge())
                        }
                        sharedViewModel.user?.let {
                            db.collection("Users").document(it.userId).set(incrementMinsCooked, SetOptions.merge())
                        }
                        navController.navigate("homeScreen")
                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = TasteBudOrange
                    )
                ) {
                    Text("Done!", fontSize = 24.sp, modifier = Modifier.padding(15.dp))
                }
            }
        }
        // Back and forward buttons
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val coroutineScope = rememberCoroutineScope()

            // Display the "Completed" button when the "Next" button is disabled
            Button(
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                }, enabled = pagerState.currentPage > 0, colors = ButtonDefaults.buttonColors(
                    containerColor = TasteBudGreen
                )

            ) {
                Text(
                    "Back", fontSize = 24.sp, modifier = Modifier.padding(15.dp)
                )
            }
            Button(
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }, enabled = pagerState.currentPage < pageCount - 1, colors = ButtonDefaults.buttonColors(
                    containerColor = TasteBudGreen
                )

            ) {
                Text(
                    "Next", fontSize = 24.sp, modifier = Modifier.padding(15.dp)
                )
            }
        }
    }
}
