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
import androidx.compose.material3.Button
import androidx.compose.material3.Text
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
import com.example.tastebud.screens.SharedViewModel

import kotlinx.coroutines.launch
import com.example.tastebud.data.Instruction

@Composable
fun FlashcardsScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    NavBarScaffold(navController) { FlashcardContent(navController, it, sharedViewModel) }
}

@Composable
fun FlashcardContent(navController: NavController, innerPadding: PaddingValues, sharedViewModel: SharedViewModel) {
    Column(
        modifier = Modifier.padding(innerPadding),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            "Intructions",
            modifier = Modifier.padding(15.dp, 0.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        if (sharedViewModel.recipe != null) {
            // TODO: use the recipeId to query the database and retrieve the steps for that recipe.
            // Use dummy data for now
//            val steps = listOf(
//                listOf("1", "Chop pumpkin using a food processor until rice-like."),
//                listOf("2", "Saut pumpkin in hot olive oil for 3 minutes. Set aside and let cool."),
//                listOf("3", "Mix feta and mozzarella; add, one at a time, eggs."),
//                listOf("4", "Mix and combine."),
//                listOf("5", "Add pumpkin and spices, mix well until well blended."),
//                listOf(
//                    "6",
//                    "Evenly spoon the mixture into the greased muffin tin molds. Press pizza dough down evenly and firmly (the pressing down firmly is very important to make sure they stick together)."
//                ),
//                listOf("7", "Place in the oven and bake for 30 minutes at 200C."),
//                listOf(
//                    "8",
//                    "Remove the pizza bites from the oven and let set until cool (this is also very important  let the pizza bites set in their pan for 5  10 minutes before removing  if you take them out while they are too hot they will break)."
//                )
//            )
            val steps = sharedViewModel.recipe?.steps
            if(steps != null){
                RecipeStepsPager(steps = steps)
            }


        }
    }
}

@Composable
fun RecipeStepsPager(steps: List<Instruction>) {
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
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp) // Add left and right padding
            //.height(100.dp)
    ) {
        // Horizontal pager for recipe steps
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize().weight(3f)

        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(16.dp))
            ) {
                Text(text = "${steps[page].stepNumber}: ${steps[page].step}",lineHeight = 30.sp, fontFamily = FontFamily.SansSerif, fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier
                    .padding(20.dp)

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
                val color = if (pagerState.currentPage == stepNumber - 1) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier.padding(8.dp)
                        .background(color, CircleShape)
                        .size(10.dp)
                )
            }
        }

        // Back and forward buttons
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            val coroutineScope = rememberCoroutineScope()
            Button(
                onClick = { coroutineScope.launch{
                    pagerState.animateScrollToPage(pagerState.currentPage - 1)}},
                enabled = pagerState.currentPage > 0
            ) {
                Text("Back", fontSize = 24.sp,modifier = Modifier
                    .padding(15.dp))
            }
            Button(
                onClick = {coroutineScope.launch{
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)} },
                enabled = pagerState.currentPage < pageCount - 1
            ) {
                Text("Next",fontSize = 24.sp, modifier = Modifier
                    .padding(15.dp))
            }
        }
    }
}

