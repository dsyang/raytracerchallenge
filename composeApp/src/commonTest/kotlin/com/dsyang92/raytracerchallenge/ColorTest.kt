package com.dsyang92.raytracerchallenge

import kotlin.test.Test
import kotlin.test.assertEquals

class ColorTest {
    @Test
    fun `colors are rgb tuples`() {
        val c = color(-0.5, 0.4, 1.7)
        assertEquals(c.red(), -0.5)
        assertEquals(c.green(), 0.4)
        assertEquals(c.blue(), 1.7)
    }

    @Test
    fun `adding colors`() {
        val c1 = color(0.9, 0.6, 0.75)
        val c2 = color(0.7, 0.1, 0.25)
        assertEquals(c1 + c2, color(1.6, 0.7, 1.0))
    }

    @Test
    fun `subtracting colors`() {
        val c1 = color(0.9, 0.6, 0.75)
        val c2 = color(0.7, 0.1, 0.25)
        (c1 - c2) eq color(0.2, 0.5, 0.5)
    }

    @Test
    fun `multiplying colors by a scalar`() {
        val c = color(0.2, 0.3, 0.4)
        assertEquals(c * 2.0, color(0.4, 0.6, 0.8))
    }

    @Test
    fun `multiplying colors by colors`() {
        val c1 = color(1.0, 0.2, 0.4)
        val c2 = color(0.9, 1.0, 0.1)
        (c1 mult c2) eq color(0.9, 0.2, 0.04)
    }
}


