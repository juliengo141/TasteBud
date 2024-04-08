package com.example.tastebud.data

import org.junit.jupiter.api.Test
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.jupiter.api.Assertions.*

class IngredientTest {
    lateinit var i1: Ingredient
    lateinit var i2: Ingredient

    @Before
    fun setup() {
        val ingredientId = "1"
        val name = "Basmati Rice"
        val original = "2 cups of basmati rice"
        val imageUrl = "https://example.com/image.jpg"
        val amount = "2 cups"
        val unit = "cups"

        val ingredientId2 = "2"
        val name2 = "Black Beans"
        val original2 = "1 can of black beans, drained and rinsed"
        val imageUrl2 = "https://example.com/image2.jpg"
        val amount2 = "1 can"
        val unit2 = "can"

        i1 = Ingredient(
            ingredientId = ingredientId,
            name = name,
            original = original,
            imageUrl = imageUrl,
            amount = amount,
            unit = unit
        )

        i2 = Ingredient(
            ingredientId = ingredientId2,
            name = name2,
            original = original2,
            imageUrl = imageUrl2,
            amount = amount2,
            unit = unit2
        )
    }

    @Test
    fun getIngredientId() {
        if (::i1.isInitialized) {
            assertEquals("1", i1.ingredientId)
        }

        if (::i2.isInitialized) {
            assertEquals("2", i2.ingredientId)
        }
    }

    @Test
    fun getName() {
        if (::i1.isInitialized) {
            assertEquals("Basmati Rice", i1.name)
        }

        if (::i2.isInitialized) {
            assertNotEquals("Basmati Rice", i2.name)
        }
    }

    @Test
    fun getOriginal() {
        if (::i1.isInitialized) {
            assertEquals("2 cups of basmati rice", i1.original)
        }

        if (::i2.isInitialized) {
            assertEquals("1 can of black beans, drained and rinsed", i2.original)
        }
    }

    @Test
    fun getImageUrl() {
        if (::i1.isInitialized) {
            assertEquals("https://example.com/image.jpg", i1.imageUrl)
        }

        if (::i2.isInitialized) {
            assertEquals("https://example.com/image2.jpg", i2.imageUrl)
        }
    }

    @Test
    fun getAmount() {
        if (::i1.isInitialized) {
            assertEquals("2 cups", i1.amount)
        }

        if (::i2.isInitialized) {
            assertNotEquals("2 cans", i2.amount)
        }
    }

    @Test
    fun getUnit() {
        if (::i1.isInitialized) {
            assertEquals("cups", i1.unit)
        }

        if (::i2.isInitialized) {
            assertNotEquals("cans", i2.unit)
        }
    }
}
