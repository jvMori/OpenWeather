package com.jvmori.openweather.currentWeather.data.usecases

import com.jvmori.openweather.common.util.Actions
import com.jvmori.openweather.common.data.network.Resource
import com.jvmori.openweather.currentWeather.domain.repositories.CurrentWeatherRepository
import com.jvmori.openweather.currentWeather.domain.usecases.FetchNewWeatherUseCase

class FetchNewWeatherUseCaseImpl(
    private val repository: CurrentWeatherRepository
) : FetchNewWeatherUseCase {
    override suspend fun fetchCurrentWeather(city: String): Resource<Actions> {
        return repository.fetchNewWeather(city)
    }
}