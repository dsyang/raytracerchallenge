package com.dsyang92.compose

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform