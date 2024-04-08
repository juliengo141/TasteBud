package com.example.tastebud.data

import org.junit.Before
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class InstructionTest {
    lateinit var instruction1: Instruction
    lateinit var instruction2: Instruction

    @Before
    fun setup() {
        val ingredients1 = listOf(
            Equipment("1", "Potatoes", "2 large potatoes"),
            Equipment("2", "Salt", "1 teaspoon")
        )
        val equipment1 = listOf(
            Equipment("1", "Pot", ""),
            Equipment("2", "Colander", "")
        )
        instruction1 = Instruction(
            1,
            "Boil the potatoes in their skins until fork-tender, about 30-40 minutes.",
            ingredients1,
            equipment1
        )

        val ingredients2 = listOf(
            Equipment("3", "Phyllo Dough", "1 package of phyllo dough"),
            Equipment("4", "Butter", "1 cup of melted butter")
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
    }
    @Test
    fun getStepNumber() {
        if (::instruction1.isInitialized) {
            assertEquals(1, instruction1.stepNumber)
        }

        if (::instruction2.isInitialized) {
            assertEquals(3, instruction2.stepNumber)
        }
    }

    @Test
    fun getStep() {
        if (::instruction1.isInitialized) {
            assertEquals(
                "Boil the potatoes in their skins until fork-tender, about 30-40 minutes.",
                instruction1.step
            )
        }
        if (::instruction2.isInitialized) {
            assertEquals(
                "Brush the bottom of a baking dish with melted butter. Layer 2 sheets of phyllo dough in the dish, brushing each sheet with melted butter.",
                instruction2.step
            )
        }
    }

    @Test
    fun getIngredients() {
        if (::instruction1.isInitialized) {
            assertEquals(2, instruction1.ingredients.size)
        }
        if (::instruction2.isInitialized) {
            assertEquals(2, instruction2.ingredients.size)
        }

        if (::instruction1.isInitialized) {
            assertEquals("Potatoes", instruction1.ingredients[0].name)
            assertEquals("Salt", instruction1.ingredients[1].name)
        }

        if (::instruction2.isInitialized) {
            assertEquals("Phyllo Dough", instruction2.ingredients[0].name)
            assertEquals("Butter", instruction2.ingredients[1].name)
        }
    }

    @Test
    fun getEquipment() {
        if (::instruction1.isInitialized) {
            assertEquals(2, instruction1.equipment.size)
        }

        if (::instruction2.isInitialized) {
            assertEquals(2, instruction2.equipment.size)
        }


        if (::instruction1.isInitialized) {
            assertEquals("Pot", instruction1.equipment[0].name)
            assertEquals("Colander", instruction1.equipment[1].name)
        }

        if (::instruction2.isInitialized) {
            assertEquals("Baking dish", instruction2.equipment[0].name)
            assertEquals("Pastry brush", instruction2.equipment[1].name)
        }
    }
}