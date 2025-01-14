package dev.stasleonov.bee.di

import dev.stasleonov.bee.domain.AudioPlayer
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val targetModule = module {
    single<AudioPlayer> { AudioPlayer(context = androidContext()) }
}