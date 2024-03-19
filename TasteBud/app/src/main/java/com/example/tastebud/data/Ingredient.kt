package com.example.tastebud.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@kotlinx.parcelize.Parcelize
data class Ingredient(
    val ingredientId: String,
    val name: String,
    val original: String, // name with amount measurements
    val imageUrl: String = "",
    val amount: String,
    val unit: String
): Parcelable