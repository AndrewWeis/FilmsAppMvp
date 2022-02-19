package com.example.filmsapp.application

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.example.filmsapp.di.modelsModule
import com.example.filmsapp.di.networkModule
import com.example.filmsapp.di.presenterModule
import com.example.utils.image_loader.GlideLoaderCreator
import com.example.utils.image_loader.ImageLoader
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Application класс
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        ImageLoader.loaderCreator = GlideLoaderCreator()

        context = applicationContext

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(modelsModule, networkModule, presenterModule))
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}