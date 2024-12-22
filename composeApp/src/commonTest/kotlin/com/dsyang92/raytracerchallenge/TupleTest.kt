package com.dsyang92.raytracerchallenge

import kotlin.math.sqrt
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TupleTest {

    @Test
    fun `a tuple with w as 1 is a point`() {
        val a = Tuple(4.3, -4.2, 3.1, 1.0)
        assertTrue(a.x eq 4.3)
        assertTrue(a.y eq -4.2)
        assertTrue(a.z eq 3.1)
        assertTrue(a.w eq 1.0)
        assertTrue(a.isPoint())
        assertFalse(a.isVector())
    }

    @Test
    fun `a tuple with w as 0 is a vector`() {
        val a = Tuple(4.3, -4.2, 3.1, 0.0)
        assertTrue(a.x eq 4.3)
        assertTrue(a.y eq -4.2)
        assertTrue(a.z eq 3.1)
        assertTrue(a.w eq 0.0)
        assertTrue(a.isVector())
        assertFalse(a.isPoint())
    }

    @Test
    fun `point creates tuples with w as 1`() {
        val p = Tuple.p(4.0, -4.0, 3.0)
        assertEquals(p, Tuple(4.0, -4.0, 3.0, 1.0))
    }

    @Test
    fun `vector creates tuples with w as 1`() {
        val p = Tuple.v(4.0, -4.0, 3.0)
        assertEquals(p, Tuple(4.0, -4.0, 3.0, 0.0))
    }

    @Test
    fun `tuples equality works`() {
        val v1 = Tuple.v(10.2, 42.8, 39.23)
        val v2 = Tuple.v(10.2, 42.8, 39.23)
        val p1 = Tuple.p(41.0, 0.02, 81.142)

        assertTrue(v1 eq v2)
        assertFalse(v1 eq p1)
        assertFalse(v2 eq p1)
    }

    @Test
    fun `tuple plus`() {
        val a1 = Tuple.p(3.0, -2.0, 5.0)
        val a2 = Tuple.v(-2.0, 3.0, 1.0)
        assertTrue((a1 + a2) eq Tuple(1.0, 1.0, 6.0, 1.0))
    }

    @Test
    fun `subtracting two points is a vector`() {
        val p1 = Tuple.p(3.0, 2.0, 1.0)
        val p2 = Tuple.p(5.0, 6.0, 7.0)
        assertTrue((p1 - p2) eq Tuple.v(-2.0, -4.0, -6.0))
    }

    @Test
    fun `subtracting a vector from a point is a vector`() {
        val p = point(3, 2, 1)
        val v = vector(5, 6, 7)
        assertEquals((p - v), point(-2, -4, -6))
    }

    @Test
    fun `subtracting two vectors is a vector`() {
        val v1 = vector(3, 2, 1)
        val v2 = vector(5, 6, 7)
        assertEquals((v1 - v2), vector(-2, -4, -6))
    }

    @Test
    fun `negate is subtracting from 0 vector`() {
        val zero = vector(0, 0, 0)
        val v = vector(1, -2, 3)
        val vnot = Tuple(-1.0, 2.0, -3.0, 0.0)
        assertEquals(zero - v, vnot)
        assertEquals(-v, vnot)
    }

    @Test
    fun `multiplying a tuple by a scalar `() {
        val a = Tuple(1.0, -2.0, 3.0, -4.0)
        val s = 3.5
        assertEquals(a * s, s * a)
        assertEquals(a * s, Tuple(3.5, -7.0, 10.5, -14.0))
    }

    @Test
    fun `dividing a tuple by a scalar`() {
        val a = Tuple(1.0, -2.0, 3.0, -4.0)
        val f = 0.5
        assertEquals(a * f, f * a)
        assertEquals(a * f, a / 2.0)
        assertEquals(a / 2.0, Tuple(0.5, -1.0, 1.5, -2.0))
    }


    @Test
    fun `magnitudes of vectors`() {
        val ux = vector(1, 0, 0)
        val uy = vector(0, 1, 0)
        val uz = vector(0, 0, 1)
        val v1 = vector(1, 2, 3)
        val v2 = vector(-1, -2, -3)

        assertEquals(ux.magnitude(), 1.0)
        assertEquals(uy.magnitude(), 1.0)
        assertEquals(uz.magnitude(), 1.0)
        assertEquals(v1.magnitude(), sqrt(14.0))
        assertEquals(v2.magnitude(), sqrt(14.0))
    }

    @Test
    fun `normalizing a vector along an axis`() {
        val v = vector(4, 0, 0)
        assertEquals(v.norm(), vector(1, 0, 0))
    }

    @Test
    fun `normalizing a vector`() {
        val v = vector(1, 2, 3)
        val mag = v.magnitude()
        assertEquals(v.norm(), Tuple.v(1 / mag, 2 / mag, 3 / mag))
    }

    @Test
    fun `magnitude of a normalized vector`() {
        val v = vector(1, 2, 3)
        val vnorm = v.norm()
        assertEquals(vnorm.magnitude(), 1.0)
    }

    @Test
    fun `vector dot product`() {
        val a = vector(1, 2, 3)
        val b = vector(2, 3, 4)
        assertEquals(a * b, 20.0)
    }

    @Test
    fun `vector cross product`() {
        val a = vector(1, 2, 3)
        val b = vector(2, 3, 4)
        assertEquals( a x b, vector(-1, 2, -1))
        assertEquals( b x a, vector(1, -2, 1))
    }
}