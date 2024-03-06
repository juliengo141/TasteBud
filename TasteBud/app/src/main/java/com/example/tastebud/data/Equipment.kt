package com.example.tastebud.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients")
data class Equipment(
    @PrimaryKey @ColumnInfo(name = "id") val equipmentId: String,
    val name: String,
    val imageUrl: String = "",
) { }