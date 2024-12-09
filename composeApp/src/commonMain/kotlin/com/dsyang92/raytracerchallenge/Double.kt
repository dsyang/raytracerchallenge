package com.dsyang92.raytracerchallenge

import kotlin.math.abs

infix fun Double.eq(other: Double) = abs(this - other) < 0.000001
infix fun Double.ge(other: Double) = this > other || this.eq(other)