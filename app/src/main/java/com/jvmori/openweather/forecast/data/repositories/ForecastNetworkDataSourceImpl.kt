package com.jvmori.openweather.forecast.data.repositories

import com.jvmori.openweather.currentWeather.domain.entities.Coordinates
import com.jvmori.openweather.forecast.data.network.ForecastApi
import com.jvmori.openweather.forecast.data.network.response.ForecastResponse
import com.jvmori.openweather.forecast.domain.repositories.ForecastNetworkDataSource

class ForecastNetworkDataSourceImpl(
    private val api: ForecastApi
) : ForecastNetworkDataSource {
    override suspend fun fetchForecast(coordinates: Coordinates): ForecastResponse {
        return api.fetchForecast(coordinates.lat, coordinates.lon)
    }
}