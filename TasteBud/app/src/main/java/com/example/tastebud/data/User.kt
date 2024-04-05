package com.example.tastebud.data

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class User(
    val userId: String,
    val email: String,
    val fullName: String,
    val dietaryRestrictions: List<String>,
    val minsCooked: Long,
    val completedCount: Long,
    val startedCount: Long
) : Parcelable
