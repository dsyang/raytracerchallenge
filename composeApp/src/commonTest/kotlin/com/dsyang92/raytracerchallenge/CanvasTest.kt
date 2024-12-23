package com.dsyang92.raytracerchallenge

import kotlin.test.Test
import kotlin.test.assertEquals

class CanvasTest {

    @Test
    fun `canvas creation`() {
        val c = Canvas(10, 20)
        assertEquals(c.width, 10)
        assertEquals(c.height, 20)
    }

    @Test
    fun `write pixel to canvas`() {
        val c = Canvas(10, 20)
        val red = color(1.0, 0.0, 0.0)
        c[2, 3] = red
        assertEquals(c[2, 3], red)
    }

    @Test
    fun `canvas to ppm generates proper header`() {
        val c = Canvas(5, 3)
        val ppm = c.toPPM().lines().take(3).joinToString("\n")
        assertEquals(
            ppm,
            """
                P3
                5 3
                255
            """.trimIndent()
        )
    }

    @Test
    fun `canvas to ppm adds pixel data`() {
        val c = Canvas(5, 3)
        val c1 = color(1.5, 0.0, 0.0)
        val c2 = color(0.0, 0.5, 0.0)
        val c3 = color(-0.5, 0.0, 1.0)
        c[0, 0] = c1
        c[2, 1] = c2
        c[4, 2] = c3
        val ppm = c.toPPM()

        assertEquals(
            ppm.lines().takeLast(3).joinToString("\n"), """
            255 0 0 0 0 0 0 0 0 0 0 0 0 0 0
            0 0 0 0 0 0 0 128 0 0 0 0 0 0 0
            0 0 0 0 0 0 0 0 0 0 0 0 0 0 255
        """.trimIndent()
        )
    }

    @Test
    fun `splitting long lines in PPM files`() {
        val c = Canvas(10, 2) { color(1.0, 0.8, 0.6) }
        val ppm = c.toPPM()

        assertEquals(
            ppm.lines().takeLast(4).joinToString("\n"), """
            255 204 153 255 204 153 255 204 153 255 204 153 255 204 153 255 204
            153 255 204 153 255 204 153 255 204 153 255 204 153
            255 204 153 255 204 153 255 204 153 255 204 153 255 204 153 255 204
            153 255 204 153 255 204 153 255 204 153 255 204 153
        """.trimIndent()
        )

    }
}