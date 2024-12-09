package com.dsyang92.raytracerchallenge

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform