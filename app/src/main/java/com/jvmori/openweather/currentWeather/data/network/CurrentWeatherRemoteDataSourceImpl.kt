package com.jvmori.openweather.currentWeather.data.network

import com.jvmori.openweather.currentWeather.domain.entities.CurrentWeatherEntity
import com.jvmori.openweather.currentWeather.domain.repositories.CurrentWeatherRemoteDataSource

class CurrentWeatherRemoteDataSourceImpl :
    CurrentWeatherRemoteDataSource {

    override suspend fun fetchCurrentWeather(city: String): CurrentWeatherEntity {
        return CurrentWeatherEntity(city, "", 0.00f)
    }

    override suspend fun refreshCurrentWeatherList(): List<CurrentWeatherEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}