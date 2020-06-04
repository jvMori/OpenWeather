package com.jvmori.openweather.common.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jvmori.openweather.currentWeather.data.local.CurrentWeatherDao
import com.jvmori.openweather.currentWeather.data.local.CurrentWeatherData
import org.koin.dsl.module

@Database(entities = [CurrentWeatherData::class], version = 5, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun currentWeatherDao(): CurrentWeatherDao
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            WeatherDatabase::class.java,
            "open_weather.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}