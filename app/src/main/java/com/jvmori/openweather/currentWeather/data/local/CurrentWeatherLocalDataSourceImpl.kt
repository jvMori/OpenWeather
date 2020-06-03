package com.jvmori.openweather.currentWeather.data.local

import com.jvmori.openweather.currentWeather.domain.repositories.CurrentWeatherLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class CurrentWeatherLocalDataSourceImpl :
    CurrentWeatherLocalDataSource {

    private val cities = listOf<String>("Gdańsk", "Warszawa", "Kraków", "Wrocław", "Łódź")

    override suspend fun fetchCurrentWeather(city: String): Flow<CurrentWeatherData> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fetchAllWeather(): Flow<List<CurrentWeatherData>> {
       return flow {
           listOf(
               CurrentWeatherData("Gdańsk", "", 0.00f)
           )
       }
    }

    override suspend fun saveCurrentWeather(data: CurrentWeatherData) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun saveAllWeather(data: List<CurrentWeatherData>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}