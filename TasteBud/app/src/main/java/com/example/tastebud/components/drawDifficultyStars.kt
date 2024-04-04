package com.example.tastebud.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tastebud.ui.theme.TasteBudGreen

@Composable
fun DifficultyStars(difficulty: Int) {
    val starIcon = Icons.Filled.Star
    val emptyStarIcon = Icons.Filled.StarBorder

    val starColor = TasteBudGreen

    Row {
        Box {
            if (difficulty > 0) {
                Icon(starIcon, contentDescription = null, tint = starColor, modifier = Modifier.size(25.dp))

            } else {
                Icon(emptyStarIcon, contentDescription = null, tint = starColor, modifier = Modifier.size(25.dp))
            }
        }
        Box {
            if (difficulty > 5) {
                Icon(starIcon, contentDescription = null, tint = starColor, modifier = Modifier.size(25.dp))
            } else {
                Icon(emptyStarIcon, contentDescription = null, tint = starColor, modifier = Modifier.size(25.dp))
            }
        }
        Box {
            if (difficulty > 10) {
                Icon(starIcon, contentDescription = null, tint = starColor, modifier = Modifier.size(25.dp))
            } else {
                Icon(emptyStarIcon, contentDescription = null, tint = starColor, modifier = Modifier.size(25.dp))
            }
        }
        Box {
            if (difficulty > 15) {
                Icon(starIcon, contentDescription = null, tint = starColor, modifier = Modifier.size(25.dp))
            } else {
                Icon(emptyStarIcon, contentDescription = null, tint = starColor, modifier = Modifier.size(25.dp))
            }
        }
        Box {
            if (difficulty > 20) {
                Icon(starIcon, contentDescription = null, tint = starColor, modifier = Modifier.size(25.dp))
            } else {
                Icon(emptyStarIcon, contentDescription = null, tint = starColor, modifier = Modifier.size(25.dp))
            }
        }
    }
}