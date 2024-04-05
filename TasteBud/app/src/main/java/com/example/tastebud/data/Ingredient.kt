package com.example.tastebud.data

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class Ingredient(
    val ingredientId: String,
    val name: String,
    val original: String, // name with amount measurements
    val imageUrl: String = "",
    val amount: String,
    val unit: String
) : Parcelable
