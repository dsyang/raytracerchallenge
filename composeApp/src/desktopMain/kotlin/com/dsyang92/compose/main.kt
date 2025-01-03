package com.dsyang92.compose

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun mainCompose() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "RTchallenge",
    ) {
        App()
    }
}

fun main() = mainCompose()