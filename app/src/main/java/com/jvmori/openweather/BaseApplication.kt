package com.jvmori.openweather

import android.app.Application
import com.jvmori.openweather.common.presentation.di.networkModule
import com.jvmori.openweather.currentWeather.presentation.di.currentWeatherModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(
                listOf(
                    networkModule,
                    currentWeatherModule
                )
            )
        }
    }
}