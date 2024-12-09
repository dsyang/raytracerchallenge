package com.dsyang92.raytracerchallenge

data class Tuple(
    val x: Double,
    val y: Double,
    val z: Double,
    val w: Double,
) {
    companion object {
        fun point(x: Double,y: Double,z: Double) = Tuple(x, y, z, 1.0)
        fun vector(x: Double, y: Double, z: Double) = Tuple(x, y, z, 0.0)
    }
}

fun Tuple.isPoint(): Boolean = w == 1.0
fun Tuple.isVector(): Boolean = w == 0.0