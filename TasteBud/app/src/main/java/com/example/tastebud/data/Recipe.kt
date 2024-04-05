package com.example.tastebud.data

import SpoonacularAPI
import android.os.Parcelable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@kotlinx.parcelize.Parcelize
data class Recipe(
    val recipeId: String,
    val title: String,
    val imageUrl: String,
    val estimatedMins: Long,
    val servings: Long,
    val cuisines: List<String>,
    val diets: List<String>,
    val vegetarian: Boolean,
    val vegan: Boolean,
    val glutenFree: Boolean,
    val dairyFree: Boolean,
    val veryHealthy: Boolean,
    val cheap: Boolean,
    val difficulty: String,
    val ingredients: List<Ingredient>,
    val steps: List<Instruction>,
) : Parcelable

var retrofit = Retrofit.Builder()
    .baseUrl("https://api.spoonacular.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

var api = retrofit.create(SpoonacularAPI::class.java)
