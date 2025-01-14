package dev.stasleonov.bee.di

import dev.stasleonov.bee.domain.AudioPlayer
import org.koin.dsl.module

actual val targetModule = module {
    single<AudioPlayer> { AudioPlayer() }
}