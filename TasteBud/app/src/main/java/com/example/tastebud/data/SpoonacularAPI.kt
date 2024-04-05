import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SpoonacularAPI {
    @GET("recipes/findByIngredients")
    fun getRecipesByIngredients(
        @Query("apiKey") apiKey: String?,
        @Query("ingredients") ingredients: String?,
        @Query("number") number: Int
    ): Call<List<Any?>?>?
}
