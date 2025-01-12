package dev.stasleonov.bee

enum class Platform {
    ANDROID, IOS, DESKTOP, WASM
}

expect fun getPlatform(): Platform