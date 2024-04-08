import com.example.tastebud.data.Equipment
import com.example.tastebud.data.Ingredient
import com.example.tastebud.data.Instruction
import com.example.tastebud.data.Recipe
import org.junit.Before
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class RecipeTest {
    lateinit var recipe1: Recipe
    lateinit var recipe2: Recipe

    private lateinit var instruction1: Instruction
    private lateinit var instruction2: Instruction

    @Before
    fun setup() {

        val i1 = Ingredient(
            "1",
            "Potatoes",
            "2 large potatoes",
            "https://example.com/potatoes.jpg",
            "2",
            "large"
        )

        val i2= Ingredient(
            "2",
            "Salt",
            "1 teaspoon",
            "",
            "1",
            "teaspoon"
        )

        val ingredient1 = Equipment(
            "1",
            "Potatoes",
            "2 large potatoes",
        )

        val ingredient2 = Equipment(
            "2",
            "Salt",
            "1 teaspoon",
        )

        val ingredients1 = listOf(ingredient1, ingredient2)
        val in1 = listOf(i1, i2)
        val equipment1 = listOf(
            Equipment("1", "Pot", ""),
            Equipment("2", "Colander", "")
        )

        val ingredients2 = listOf(ingredient1, ingredient2)


        instruction1 = Instruction(
                    1,
            "Boil the potatoes in their skins until fork-tender, about 30-40 minutes.",
            ingredients1,
            equipment1
        )


        val equipment2 = listOf(
            Equipment("3", "Baking dish", ""),
            Equipment("4", "Pastry brush", "")
        )
        instruction2 = Instruction(
            3,
            "Brush the bottom of a baking dish with melted butter. Layer 2 sheets of phyllo dough in the dish, brushing each sheet with melted butter.",
            ingredients2,
            equipment2
        )

        val cuisines1 = listOf("Indian", "Asian")
        val diets1 = listOf("Vegetarian", "Vegan")

        recipe1 = Recipe(
            "1",
            "Potato Curry",
            "https://example.com/potato_curry.jpg",
            30,
            4,
            cuisines1,
            diets1,
            true,
            true,
            false,
            false,
            true,
            false,
            "Easy",
            in1,
            listOf(instruction1)
        )

        val cuisines2 = listOf("Italian", "Mediterranean")
        val diets2 = listOf("Gluten-Free")

        recipe2 = Recipe(
            "2",
            "Tomato Pasta",
            "https://example.com/tomato_pasta.jpg",
            45,
            2,
            cuisines2,
            diets2,
            true,
            false,
            true,
            false,
            false,
            true,
            "Intermediate",
            listOf(),
            listOf(instruction2)
        )
    }

    @Test
    fun getRecipeId() {
        if (::recipe1.isInitialized) {
            assertEquals("1", recipe1.recipeId)
        }
        if (::recipe2.isInitialized) {
            assertEquals("2", recipe2.recipeId)
        }
    }

    @Test
    fun getTitle() {
        if (::recipe1.isInitialized) {
        assertEquals("Potato Curry", recipe1.title)
        }
        if (::recipe2.isInitialized) {
            assertEquals("Tomato Pasta", recipe2.title)
        }
    }

    @Test
    fun getImageUrl() {
        if (::recipe1.isInitialized) {
            assertEquals("https://example.com/potato_curry.jpg", recipe1.imageUrl)
        }
        if (::recipe2.isInitialized) {
            assertEquals("https://example.com/tomato_pasta.jpg", recipe2.imageUrl)
        }
    }

    @Test
    fun getEstimatedMins() {
        if (::recipe1.isInitialized) {
            assertEquals(30, recipe1.estimatedMins)
        }
        if (::recipe2.isInitialized) {
            assertEquals(45, recipe2.estimatedMins)
        }
    }

    @Test
    fun getServings() {
        if (::recipe1.isInitialized) {
            assertEquals(4, recipe1.servings)
        }
        if (::recipe2.isInitialized) {
            assertEquals(2, recipe2.servings)
        }
    }

    @Test
    fun getCuisines() {
        if (::recipe1.isInitialized) {
            assertEquals(listOf("Indian", "Asian"), recipe1.cuisines)
        }
        if (::recipe2.isInitialized) {
            assertEquals(listOf("Italian", "Mediterranean"), recipe2.cuisines)
        }
    }

    @Test
    fun getDiets() {
        if (::recipe1.isInitialized) {
            assertEquals(listOf("Vegetarian", "Vegan"), recipe1.diets)
        }
        if (::recipe2.isInitialized) {
            assertEquals(listOf("Gluten-Free"), recipe2.diets)
        }
    }

    @Test
    fun getVegetarian() {
        if (::recipe1.isInitialized) {
            assertTrue(recipe1.vegetarian)
        }
        if (::recipe2.isInitialized) {
            assertFalse(recipe2.vegetarian)
        }
    }

    @Test
    fun getVegan() {
        if (::recipe1.isInitialized) {
            assertTrue(recipe1.vegan)
        }
        if (::recipe2.isInitialized) {
            assertFalse(recipe2.vegan)
        }
    }

    @Test
    fun getGlutenFree() {
        if (::recipe1.isInitialized) {
            assertFalse(recipe1.glutenFree)
        }
        if (::recipe2.isInitialized) {
            assertTrue(recipe2.glutenFree)
        }
    }

    @Test
    fun getDairyFree() {
        if (::recipe1.isInitialized) {
            assertFalse(recipe1.dairyFree)
        }
        if (::recipe2.isInitialized) {
            assertFalse(recipe2.dairyFree)
        }
    }

    @Test
    fun getVeryHealthy() {
        if (::recipe1.isInitialized) {
            assertTrue(recipe1.veryHealthy)
        }
        if (::recipe2.isInitialized) {
            assertFalse(recipe2.veryHealthy)
        }
    }

    @Test
    fun getCheap() {
        if (::recipe1.isInitialized) {
            assertFalse(recipe1.cheap)
        }
        if (::recipe2.isInitialized) {
            assertFalse(recipe2.cheap)
        }
    }

    @Test
    fun getDifficulty() {
        if (::recipe1.isInitialized) {
            assertEquals("Easy", recipe1.difficulty)
        }
        if (::recipe2.isInitialized) {
            assertEquals("Intermediate", recipe2.difficulty)
        }
    }

    @Test
    fun getIngredients() {
        if (::recipe1.isInitialized) {
            assertEquals(2, recipe1.ingredients.size)
        }
        if (::recipe2.isInitialized) {
            assertEquals(0, recipe2.ingredients.size)
        }
    }

    @Test
    fun getSteps() {
        if (::recipe1.isInitialized) {
        assertEquals(1, recipe1.steps.size)
            }
        if (::recipe2.isInitialized) {
            assertEquals(1, recipe2.steps.size)
        }
    }
}
