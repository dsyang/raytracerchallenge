package com.dsyang92.raytracerchallenge

typealias Color = Tuple

fun color(red: Double, green: Double, blue: Double): Tuple =
    Tuple(red, green, blue, 0.0)

fun Color.red() = this.x
fun Color.green() = this.y
fun Color.blue() = this.z

infix fun Color.mult(c: Color): Color = hadamardProduct(this, c)


private fun hadamardProduct(c1: Color, c2: Color): Color {
    val r = c1.red() * c2.red()
    val g = c1.green() * c2.green()
    val b = c1.blue() * c2.blue()
    return color(r,g,b)
}