package com.example.tastebud.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tastebud.data.Recipe
import com.google.firebase.auth.FirebaseUser

class SharedViewModel: ViewModel(){

    var recipe by mutableStateOf<Recipe?>(null)
        private set

    var user by mutableStateOf<FirebaseUser?>(null)
        private set
    fun addRecipe(newRecipe: Recipe){
        recipe = newRecipe;
    }

    fun addUser(newUser: FirebaseUser){
        user = newUser;
    }




}