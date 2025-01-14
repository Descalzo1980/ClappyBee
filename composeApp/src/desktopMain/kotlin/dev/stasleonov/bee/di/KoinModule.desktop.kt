package dev.stasleonov.bee.di

import dev.stasleonov.bee.domain.AudioPlayer
import org.koin.core.module.Module
import org.koin.dsl.module

actual val targetModule: Module = module {
    single<AudioPlayer> { AudioPlayer() }
}