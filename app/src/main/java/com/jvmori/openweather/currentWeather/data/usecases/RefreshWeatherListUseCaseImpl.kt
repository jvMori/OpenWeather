package com.jvmori.openweather.currentWeather.data.usecases

import com.jvmori.openweather.common.util.Actions
import com.jvmori.openweather.common.data.network.Resource
import com.jvmori.openweather.currentWeather.domain.repositories.CurrentWeatherRepository
import com.jvmori.openweather.currentWeather.domain.usecases.RefreshWeatherListUseCase

class RefreshWeatherListUseCaseImpl(
    private val repository: CurrentWeatherRepository
) : RefreshWeatherListUseCase {

    override suspend fun refreshCurrentWeatherList(): Resource<Actions> {
        return repository.refreshCurrentWeatherList()
    }
}