package com.jvmori.openweather.currentWeather.domain.usecases

import com.jvmori.openweather.common.util.Actions
import com.jvmori.openweather.common.data.network.Resource

interface RefreshWeatherListUseCase {
    suspend fun refreshCurrentWeatherList(): Resource<Actions>
}