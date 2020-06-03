package com.jvmori.openweather.currentWeather.data.repositories

import com.jvmori.openweather.common.data.Resource
import com.jvmori.openweather.currentWeather.domain.entities.CurrentWeatherEntity
import com.jvmori.openweather.currentWeather.domain.repositories.CurrentWeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map

class CurrentWeatherRepositoryImpl :
    CurrentWeatherRepository {

    private val cities = listOf<String>("Gdańsk", "Warszawa", "Kraków", "Wrocław", "Łódź")

    override suspend fun fetchCurrentWeather(city: String): Resource<CurrentWeatherEntity> {
        return Resource.success(CurrentWeatherEntity(city, "", 12.0f))
    }

    override fun fetchAllWeather(): Flow<Resource<CurrentWeatherEntity>> {
        return cities.asFlow().map {
            Resource.loading(null)
            fetchCurrentWeather(it)
        }
    }

    override suspend fun refreshCurrentWeatherList(): List<CurrentWeatherEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}