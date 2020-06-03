package com.jvmori.openweather.currentWeather.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.jvmori.openweather.common.data.Resource
import com.jvmori.openweather.currentWeather.domain.entities.CurrentWeatherEntity
import com.jvmori.openweather.currentWeather.domain.repositories.CurrentWeatherRepository

class CurrentWeatherViewModel(
    private val repository: CurrentWeatherRepository
) : ViewModel() {

    fun fetchWeather(): LiveData<Resource<List<CurrentWeatherEntity>>> {
        return repository.fetchAllWeather().asLiveData()
    }
}