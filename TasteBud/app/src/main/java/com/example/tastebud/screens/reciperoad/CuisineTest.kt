package com.example.tastebud.screens.reciperoad

import org.junit.jupiter.api.Assertions.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import com.example.tastebud.screens.reciperoad.Cuisine


class CuisineTest {
    lateinit var c1: Cuisine
    lateinit var c2: Cuisine


    @Before
    fun setup() {
        c1 = Cuisine(
            title = "Test Cuisine",
            image = "test_image_url",
            disabled = true,
            disabledImage = "test_disabled_image_url"
        )

        c1 = Cuisine(
            title = "Test Cuisine2",
            image = "test_image_url2",
            disabled = false,
            disabledImage = "test_disabled_image_url2"
        )
    }

    @org.junit.jupiter.api.Test
    fun getTitle() {
        if (::c1.isInitialized) {
        assertEquals("Test Cuisine", c1.title)
            }

        if (::c2.isInitialized) {
            assertEquals("Test Cuisine2", c2.title)
        }
    }

    @org.junit.jupiter.api.Test
    fun getImage() {
        if (::c1.isInitialized) {
            assertEquals("test_image_url", c1.image)
        }

        if (::c2.isInitialized) {
            assertNotEquals("test_image_url3", c2.image)
            assertEquals("test_image_url2", c2.image)
        }
    }

    @org.junit.jupiter.api.Test
    fun getDisabled() {
        if (::c1.isInitialized) {
            assertEquals(true, c1.disabled)
        }

        if (::c2.isInitialized) {
            assertEquals(false, c2.disabled)
        }
    }

    @org.junit.jupiter.api.Test
    fun getDisabledImage() {
        if (::c1.isInitialized) {
            assertEquals("test_disabled_image_url", c1.disabledImage)
        }

        if (::c2.isInitialized) {
            assertEquals("test_disabled_image_url2", c2.disabledImage)
        }
    }
}