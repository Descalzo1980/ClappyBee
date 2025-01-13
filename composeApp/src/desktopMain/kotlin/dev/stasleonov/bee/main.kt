package dev.stasleonov.bee

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.stasleonov.bee.di.initializeKoin

fun main() = application {
    initializeKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "ClappyBee",
    ) {
        App()
    }
}