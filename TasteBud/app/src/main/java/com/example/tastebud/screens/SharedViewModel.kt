package com.example.tastebud.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tastebud.data.Recipe
import com.example.tastebud.data.User
import com.google.firebase.auth.FirebaseUser

class SharedViewModel : ViewModel() {
    var recipe by mutableStateOf<Recipe?>(null)
        private set
    var userId by mutableStateOf<FirebaseUser?>(null)
        private set
    var user by mutableStateOf<User?>(null)
        private set
    var cuisine by mutableStateOf<String>("")
        private set
    var country by mutableStateOf<String>("")
        private set
    var cuisineRecipes by mutableStateOf<List<Recipe?>>(emptyList())
        private set
    var recipeFound by mutableStateOf<Boolean>(true)
        private set

    fun addRecipe(newRecipe: Recipe) {
        recipe = newRecipe
    }

    fun addUserId(newUser: FirebaseUser) {
        userId = newUser
    }

    fun addUser(newUser: User) {
        user = newUser
    }

    fun addCuisine(newCuisine: String, newCountry: String) {
        cuisine = newCuisine
        country = newCountry
    }

    fun addCuisineRecipes(newRecipes: List<Recipe>) {
        cuisineRecipes = newRecipes
    }

    fun addRecipeFound(newState: Boolean) {
        recipeFound = newState
    }
}