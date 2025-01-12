package dev.stasleonov.bee.util

enum class Platform {
    ANDROID, IOS, DESKTOP, WASM
}

expect fun getPlatform(): Platform