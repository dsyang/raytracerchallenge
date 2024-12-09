package com.dsyang92.raytracerchallenge

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TupleTest {

    @Test
    fun `a tuple with w as 1 is a point`() {
        val a = Tuple(4.3, -4.2, 3.1, 1.0)
        assertEquals(a.x, 4.3)
        assertEquals(a.y, -4.2)
        assertEquals(a.z, 3.1)
        assertEquals(a.w, 1.0)
        assertTrue(a.isPoint())
        assertFalse(a.isVector())
    }

    @Test
    fun `a tuple with w as 0 is a vector`() {
        val a = Tuple(4.3, -4.2, 3.1, 0.0)
        assertEquals(a.x, 4.3)
        assertEquals(a.y, -4.2)
        assertEquals(a.z, 3.1)
        assertEquals(a.w, 0.0)
        assertTrue(a.isVector())
        assertFalse(a.isPoint())
    }

    @Test
    fun `point creates tuples with w as 1`() {
        val p = Tuple.point(4.0, -4.0, 3.0)
        assertEquals(p, Tuple(4.0, -4.0, 3.0, 1.0))
    }

    @Test
    fun `vector creates tuples with w as 1`() {
        val p = Tuple.vector(4.0, -4.0, 3.0)
        assertEquals(p, Tuple(4.0, -4.0, 3.0, 0.0))
    }

}