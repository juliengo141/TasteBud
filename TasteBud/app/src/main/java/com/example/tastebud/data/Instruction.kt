package com.example.tastebud.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@kotlinx.parcelize.Parcelize
data class Instruction(
    val stepNumber: Long,
    val step: String,
    val ingredients: List<Equipment>,
    val equipment: List<Equipment>,
)  : Parcelable