package com.jvmori.openweather.currentWeather.data.usecases

import com.jvmori.openweather.common.data.Resource
import com.jvmori.openweather.currentWeather.domain.repositories.CurrentWeatherRepository
import com.jvmori.openweather.currentWeather.domain.usecases.FetchNewWeatherUseCase

class FetchNewWeatherUseCaseImpl(
    private val repository: CurrentWeatherRepository
) : FetchNewWeatherUseCase {
    override suspend fun fetchCurrentWeather(city: String): Resource<String> {
        return repository.fetchNewWeather(city)
    }
}