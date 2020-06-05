package com.jvmori.openweather.forecast.data.usecases

import com.jvmori.openweather.common.data.Resource
import com.jvmori.openweather.currentWeather.domain.entities.Coordinates
import com.jvmori.openweather.forecast.domain.entities.WeatherDetailsEntity
import com.jvmori.openweather.forecast.domain.repositories.ForecastRepository
import com.jvmori.openweather.forecast.domain.usecases.FetchDetailsUseCase

class FetchDetailsUseCaseImpl(
    private val repository: ForecastRepository
) : FetchDetailsUseCase {
    override suspend fun fetchForecast(coordinates: Coordinates): Resource<WeatherDetailsEntity> {
        return repository.fetchForecast(coordinates)
    }
}