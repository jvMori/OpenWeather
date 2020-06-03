package com.jvmori.openweather.currentWeather.data.network

import com.jvmori.openweather.currentWeather.data.network.response.CurrentWeatherResponse
import com.jvmori.openweather.currentWeather.domain.repositories.CurrentWeatherRemoteDataSource

class CurrentWeatherRemoteDataSourceImpl(
    private val weatherApi: CurrentWeatherApi
) : CurrentWeatherRemoteDataSource {

    override suspend fun fetchCurrentWeather(city: String): CurrentWeatherResponse {
        return weatherApi.fetchWeather(city)
    }
}