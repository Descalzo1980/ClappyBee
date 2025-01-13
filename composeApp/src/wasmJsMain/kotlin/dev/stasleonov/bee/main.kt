package dev.stasleonov.bee

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import dev.stasleonov.bee.di.initializeKoin
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    initializeKoin()
    ComposeViewport(document.body!!) {
        App()
    }
}