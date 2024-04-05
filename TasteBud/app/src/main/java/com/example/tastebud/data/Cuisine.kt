package com.example.tastebud.data

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class Cuisine(
    val title: String,
    val country: String,
    val image: String,
    val disabled: Boolean,
    val disabledImage: String
) : Parcelable
