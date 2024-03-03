package com.example.tastebud.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients")
data class Ingredient(
    @PrimaryKey @ColumnInfo(name = "id") val ingredientId: String,
    val name: String,
    val original: String, // name with amount measurements
    val imageUrl: String = "",
    val amount: String,
    val unit: String
) { }