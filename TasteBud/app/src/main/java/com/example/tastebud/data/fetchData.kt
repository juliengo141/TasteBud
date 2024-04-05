package com.example.tastebud.data

import SpoonacularAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun fetchRecipes(ingredientsList: List<String>, callback: (List<Int>?) -> Unit) {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.spoonacular.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api = retrofit.create(SpoonacularAPI::class.java)
    val call: Call<List<Any?>?>? =
        api.getRecipesByIngredients("46ea03ff0f3148abb2c9ca808f6de8e9", ingredientsList.joinToString(), 25)
    if (call != null) {
        call.enqueue(object : Callback<List<Any?>?> {
            override fun onResponse(call: Call<List<Any?>?>?, response: Response<List<Any?>?>) {
                if (response.isSuccessful()) {
                    val recipes: List<Any?>? = response.body()
                    val ids = recipes?.mapNotNull { item ->
                        if (item is Map<*, *>) {
                            val id = item["id"]
                            if (id is Double) {
                                id.toInt()
                            } else {
                                null
                            }
                        } else {
                            null
                        }
                    }
                    callback(ids)
                } else {
                    callback(null) // Pass null to indicate failure
                }
            }

            override fun onFailure(call: Call<List<Any?>?>?, t: Throwable?) {
                callback(null) // Pass null to indicate failure
            }
        })
    } else {
        callback(null) // Pass null to indicate failure
    }
}
