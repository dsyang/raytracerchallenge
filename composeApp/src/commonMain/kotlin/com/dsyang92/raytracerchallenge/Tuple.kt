package com.dsyang92.raytracerchallenge

import kotlin.math.sqrt

expect fun Double.format(): String

data class Tuple(
    val x: Double,
    val y: Double,
    val z: Double,
    val w: Double,
) {
    companion object {
        fun p(x: Double, y: Double, z: Double) = Tuple(x, y, z, 1.0)
        fun v(x: Double, y: Double, z: Double) = Tuple(x, y, z, 0.0)
    }

    override fun toString(): String {
        return "{ x=${x.format()}, y=${y.format()}, z=${z.format()}, w=${w.format()}"
    }
}

typealias Vector = Tuple
typealias Point = Tuple

fun point(x: Int, y: Int, z: Int) = Tuple.p(x.toDouble(), y.toDouble(), z.toDouble())
fun vector(x: Int, y: Int, z: Int) = Tuple.v(x.toDouble(), y.toDouble(), z.toDouble())
fun Tuple.isPoint(): Boolean = w == 1.0
fun Tuple.isVector(): Boolean = w == 0.0

infix fun Tuple.eq(other: Tuple): Boolean =
    (this.x eq other.x) && (this.y eq other.y) && (this.z eq other.z) && (this.w eq other.w)

infix operator fun Tuple.plus(b: Tuple): Tuple {
    val ws = this.w + b.w
    if (DEBUG) {
        tupleValidity(ws)
    }
    return Tuple(this.x + b.x, this.y + b.y, this.z + b.z, ws)
}

infix operator fun Tuple.minus(b: Tuple): Tuple {
    val ws = this.w - b.w
    if (DEBUG) {
        tupleValidity(ws)
    }
    return Tuple(this.x - b.x, this.y - b.y, this.z - b.z, ws)
}

operator fun Tuple.unaryMinus(): Tuple {
    return Tuple(0 - this.x, 0 - this.y, 0 - this.z, 0 - this.w)
}

infix operator fun Tuple.times(s: Double): Tuple {
    return Tuple(s * this.x, s * this.y, s * this.z, s * this.w)
}

infix operator fun Double.times(t: Tuple): Tuple {
    return t * this
}

infix operator fun Tuple.div(s: Double): Tuple {
    return Tuple(this.x / s, this.y / s, this.z / s, this.w / s)
}

fun Tuple.magnitude(): Double {
    if (DEBUG) {
        if (!this.isVector()) {
            throw IllegalStateException("magnitude on non-vectors is invalid!")
        }
    }
    val sumOfSquares = (this.x * this.x) +
            (this.y * this.y) +
            (this.z * this.z) +
            (this.w * this.w)
    return sqrt(sumOfSquares)
}

fun Tuple.norm(): Tuple {
    val mag = magnitude()
    return Tuple(
        this.x / mag,
        this.y / mag,
        this.z / mag,
        this.w / mag
    )
}

infix operator fun Tuple.times(b: Tuple): Double {
    if (DEBUG) {
        if (!this.isVector() || !b.isVector()) {
            throw IllegalStateException(
                "dot product only makes sense on vectors: lhs=${this.isVector()}, rhs=${b.isVector()}")
        }
    }

    return (this.x * b.x) +
            (this.y * b.y) +
            (this.z * b.z) +
            (this.w * b.w)
}

infix fun Tuple.x(b: Tuple): Tuple {
    if (DEBUG) {
        if (!this.isVector() || !b.isVector()) {
            throw IllegalStateException(
                "cross product only makes sense on vectors: lhs=${this.isVector()}, rhs=${b.isVector()}")
        }
    }
    val a = this
    return Tuple.v(
        a.y * b.z - a.z * b.y,
        a.z * b.x - a.x * b.z,
        a.x * b.y - a.y * b.x
    )
}

private fun tupleValidity(w: Double) {
    if (w > 1 || w < 0) {
        throw IllegalStateException("tuple is invalid!")
    }
}