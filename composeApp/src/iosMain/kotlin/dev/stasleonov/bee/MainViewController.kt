package dev.stasleonov.bee

import androidx.compose.ui.window.ComposeUIViewController
import dev.stasleonov.bee.di.initializeKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initializeKoin() }
) { App() }