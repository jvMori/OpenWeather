package com.jvmori.openweather.forecast.domain.repositories

import com.jvmori.openweather.common.data.Resource
import com.jvmori.openweather.common.data.handleError
import com.jvmori.openweather.common.data.mapToEntity
import com.jvmori.openweather.currentWeather.domain.entities.Coordinates
import com.jvmori.openweather.forecast.domain.entities.DetailWeatherEntity

class ForecastRepositoryImpl(
    private val dataSource: ForecastNetworkDataSource
) : ForecastRepository {
    override suspend fun fetchForecast(coordinates: Coordinates): Resource<DetailWeatherEntity> {
        return try {
            val data = dataSource.fetchForecast(coordinates)?.mapToEntity()
            if (data != null) Resource.success(data) else Resource.error("", null)
        } catch (e: Exception) {
            handleError(e)
        }
    }
}