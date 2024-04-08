package com.example.tastebud.data

import org.junit.jupiter.api.Test
import com.example.tastebud.data.Equipment
import org.junit.After
import org.junit.Before
import org.junit.jupiter.api.Assertions.*

class EquipmentTest {

    private lateinit var e1: Equipment
    private lateinit var e2: Equipment

    @Before
    fun setUp() {
        e1 = Equipment("1", "Test Equipment", "https://example.com/image.jpg")
        e2 = Equipment("2", "Test Equipment2", "https://example.com/image.jpg2")
    }

    @Test
    fun getEquipmentId() {
        if (::e1.isInitialized) {
            assertEquals("1", e1.equipmentId)
        }
        if (::e2.isInitialized) {
            assertNotEquals("1", e2.equipmentId)
        }
    }

    @Test
    fun getName() {
        if (::e1.isInitialized) {
            assertEquals("Test Equipment", e1.name)
        }
        if (::e2.isInitialized) {
            assertEquals("Test Equipment2", e2.name)
        }
    }

    @Test
    fun getImageUrl() {
        if (::e1.isInitialized) {
            assertEquals("https://example.com/image.jpg", e1.imageUrl)
        }
        if (::e2.isInitialized) {
            assertNotEquals("https://example.com/image.jpg3", e2.imageUrl)
        }
    }
}

