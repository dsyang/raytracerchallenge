package com.dsyang92.raytracerchallenge

import org.openrndr.math.clamp
import kotlin.math.roundToInt

typealias Color = Tuple

fun color(red: Double, green: Double, blue: Double): Tuple =
    Tuple(red, green, blue, 0.0)

fun Color.red() = this.x
fun Color.green() = this.y
fun Color.blue() = this.z

infix fun Color.mult(c: Color): Color = hadamardProduct(this, c)

fun Color.toPPM(maxValue: Int): String {
    val r = red()
    val g = green()
    val b = blue()
    if (DEBUG_COLOR_CLAMPING) {
        if (r > 1.0 || r < 0.0) {
            throw IllegalStateException("Red component outside of range. Found: ${r}")
        }
        if (g > 1.0 || g < 0.0) {
            throw IllegalStateException("Green component outside of range. Found: ${g}")
        }
        if (b > 1.0 || b < 0.0) {
            throw IllegalStateException("Blue component outside of range. Found: ${b}")
        }
    }
    val rInt = (r.clamp(0.0, 1.0) * maxValue).roundToInt()
    val gInt = (g.clamp(0.0, 1.0) * maxValue).roundToInt()
    val bInt = (b.clamp(0.0, 1.0) * maxValue).roundToInt()
    return "$rInt $gInt $bInt"
}

private fun hadamardProduct(c1: Color, c2: Color): Color {
    val r = c1.red() * c2.red()
    val g = c1.green() * c2.green()
    val b = c1.blue() * c2.blue()
    return color(r, g, b)
}