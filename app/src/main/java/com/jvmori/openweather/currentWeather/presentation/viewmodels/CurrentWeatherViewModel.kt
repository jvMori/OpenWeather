package com.jvmori.openweather.currentWeather.presentation.viewmodels

import androidx.lifecycle.*
import com.jvmori.openweather.common.data.Actions
import com.jvmori.openweather.common.data.Resource
import com.jvmori.openweather.currentWeather.domain.entities.CurrentWeatherEntity
import com.jvmori.openweather.currentWeather.domain.usecases.FetchNewWeatherUseCase
import com.jvmori.openweather.currentWeather.domain.usecases.FetchWeatherListUseCase
import com.jvmori.openweather.currentWeather.domain.usecases.InitDefaultWeatherUseCase
import com.jvmori.openweather.currentWeather.domain.usecases.RefreshWeatherListUseCase
import kotlinx.coroutines.launch

class CurrentWeatherViewModel(
    private val fetchWeatherListUseCase: FetchWeatherListUseCase,
    private val addNewWeatherUseCase: FetchNewWeatherUseCase,
    private val refreshWeatherListUseCase: RefreshWeatherListUseCase,
    private val initDefaultWeatherUseCase: InitDefaultWeatherUseCase
) : ViewModel() {

    private val _status = MutableLiveData<Resource<Actions>>()
    val status: LiveData<Resource<Actions>> = _status

    fun fetchWeather(): LiveData<Resource<List<CurrentWeatherEntity>>> {
        return fetchWeatherListUseCase.fetchAllWeather().asLiveData()
    }

    fun addNewWeather(city: String) {
        viewModelScope.launch {
            _status.value = addNewWeatherUseCase.fetchCurrentWeather(city)
        }
    }

    fun refreshWeatherList() {
        viewModelScope.launch {
            _status.value = refreshWeatherListUseCase.refreshCurrentWeatherList()
        }
    }

    fun initDefaultWeather() {
        viewModelScope.launch {
            _status.value = initDefaultWeatherUseCase.initDefaultWeather()
        }
    }
}