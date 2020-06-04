package com.jvmori.openweather.currentWeather.data.usecases

import com.jvmori.openweather.common.data.Actions
import com.jvmori.openweather.common.data.Resource
import com.jvmori.openweather.currentWeather.domain.repositories.CurrentWeatherRepository
import com.jvmori.openweather.currentWeather.domain.usecases.InitDefaultWeatherUseCase

class InitDefaultWeatherUseCaseImpl(private val repository: CurrentWeatherRepository) :
    InitDefaultWeatherUseCase {
    override suspend fun initDefaultWeather(): Resource<Actions> {
        return repository.initDefaultWeather()
    }
}