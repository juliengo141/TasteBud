package com.example.tastebud.compose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tastebud.data.Recipe

class SharedViewModel: ViewModel(){

    var recipe by mutableStateOf<Recipe?>(null)
        private set


    fun addRecipe(newRecipe: Recipe){
        recipe = newRecipe;
    }


}