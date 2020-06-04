package com.jvmori.openweather.currentWeather.presentation.viewmodels

import androidx.lifecycle.*
import com.jvmori.openweather.common.data.Resource
import com.jvmori.openweather.currentWeather.domain.entities.CurrentWeatherEntity
import com.jvmori.openweather.currentWeather.domain.usecases.FetchNewWeatherUseCase
import com.jvmori.openweather.currentWeather.domain.usecases.FetchWeatherListUseCase
import kotlinx.coroutines.launch

class CurrentWeatherViewModel(
    private val fetchWeatherListUseCase: FetchWeatherListUseCase,
    private val addNewWeatherUseCase: FetchNewWeatherUseCase
) : ViewModel() {

    private val _status = MutableLiveData<Resource.Status>()
    val status: LiveData<Resource.Status> = _status

    fun fetchWeather(): LiveData<Resource<List<CurrentWeatherEntity>>> {
        return fetchWeatherListUseCase.fetchAllWeather().asLiveData()
    }

    fun addNewWeather(city: String) {
        viewModelScope.launch {
            _status.value = addNewWeatherUseCase.fetchCurrentWeather(city).status ?: Resource.Status.LOADING
        }
    }
}