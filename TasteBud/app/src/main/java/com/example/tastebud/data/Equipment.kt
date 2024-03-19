package com.example.tastebud.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class Equipment(
    val equipmentId: String,
    val name: String,
    val imageUrl: String = "",
) : Parcelable