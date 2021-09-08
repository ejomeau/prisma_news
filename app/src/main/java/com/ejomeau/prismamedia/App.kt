package com.ejomeau.prismamedia

import android.app.Application
import com.ejomeau.prismamedia.koin.scopedModule
import com.ejomeau.prismamedia.koin.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.koinApplication

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(koinApplication {
            androidLogger()
            androidContext(this@App)
            modules(listOf(viewModelsModule, scopedModule))
        })
    }
}