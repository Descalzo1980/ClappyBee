package dev.stasleonov.bee

import android.app.Application
import dev.stasleonov.bee.di.initializeKoin
import org.koin.android.ext.koin.androidContext

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initializeKoin {
            androidContext(this@MyApp)
        }
    }
}