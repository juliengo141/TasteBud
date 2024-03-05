package com.example.tastebud.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey @ColumnInfo(name = "id") val recipeId: String,
    val name: String,
    val imageUrl: String,
    val estimatedTime: String,
    val dietaryRestrictions: String,
    val ingredients: List<Ingredient>,
) {}