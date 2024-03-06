package com.example.tastebud.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "instructions")
data class Instruction(
    @ColumnInfo(name = "stepNum") val stepNumber: Int,
    val step: String,
    val ingredients: List<Ingredient>,
    val equipment: List<Equipment>,
) { }