package dev.stasleonov.bee

import android.app.Application
import dev.stasleonov.bee.di.initializeKoin

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initializeKoin()
    }
}