package com.jvmori.openweather.currentWeather.data.usecases

import com.jvmori.openweather.common.data.Resource
import com.jvmori.openweather.currentWeather.domain.repositories.CurrentWeatherRepository
import com.jvmori.openweather.currentWeather.domain.usecases.RefreshWeatherListUseCase

class RefreshWeatherListUseCaseImpl(
    private val repository: CurrentWeatherRepository
) : RefreshWeatherListUseCase {

    override suspend fun refreshCurrentWeatherList(): Resource<String> {
        return repository.refreshCurrentWeatherList()
    }
}