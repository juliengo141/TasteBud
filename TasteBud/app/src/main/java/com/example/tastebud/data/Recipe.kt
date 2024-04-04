package com.example.tastebud.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@kotlinx.parcelize.Parcelize
data class Recipe(
    val recipeId: String,
    val title: String,
    val imageUrl: String,
    val estimatedTime: String,
    val servings: Long,
    val cuisines: List<String>,
    val vegetarian: Boolean,
    val vegan: Boolean,
    val glutenFree: Boolean,
    val dairyFree: Boolean,
    val veryHealthy: Boolean,
    val cheap: Boolean,
    val difficulty: String,
    val ingredients: List<Ingredient>,
    val steps: List<Instruction>,
): Parcelable