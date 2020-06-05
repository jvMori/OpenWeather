package com.jvmori.openweather.currentWeather.data.usecases

import com.jvmori.openweather.common.data.network.Resource
import com.jvmori.openweather.currentWeather.domain.entities.CurrentWeatherEntity
import com.jvmori.openweather.currentWeather.domain.repositories.CurrentWeatherRepository
import com.jvmori.openweather.currentWeather.domain.usecases.FetchWeatherListUseCase
import kotlinx.coroutines.flow.Flow

class FetchWeatherListUseCaseImpl(
    private val repository: CurrentWeatherRepository
) : FetchWeatherListUseCase {

    override fun fetchAllWeather(): Flow<Resource<List<CurrentWeatherEntity>>> {
        return repository.observeAllWeather()
    }
}