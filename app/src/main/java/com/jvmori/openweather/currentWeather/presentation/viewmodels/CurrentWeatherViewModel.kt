package com.jvmori.openweather.currentWeather.presentation.viewmodels

import androidx.lifecycle.*
import com.jvmori.openweather.common.data.Resource
import com.jvmori.openweather.currentWeather.domain.entities.CurrentWeatherEntity
import com.jvmori.openweather.currentWeather.domain.usecases.FetchNewWeatherUseCase
import com.jvmori.openweather.currentWeather.domain.usecases.FetchWeatherListUseCase
import com.jvmori.openweather.currentWeather.domain.usecases.RefreshWeatherListUseCase
import kotlinx.coroutines.launch

class CurrentWeatherViewModel(
    private val fetchWeatherListUseCase: FetchWeatherListUseCase,
    private val addNewWeatherUseCase: FetchNewWeatherUseCase,
    private val refreshWeatherListUseCase: RefreshWeatherListUseCase
) : ViewModel() {

    private val _status = MutableLiveData<Resource.Status>()
    val status: LiveData<Resource.Status> = _status

    fun fetchWeather(): LiveData<Resource<List<CurrentWeatherEntity>>> {
        return fetchWeatherListUseCase.fetchAllWeather().asLiveData()
    }

    fun addNewWeather(city: String) {
        viewModelScope.launch {
            _status.value = Resource.Status.LOADING
            _status.value = addNewWeatherUseCase.fetchCurrentWeather(city).status ?: Resource.Status.LOADING
        }
    }

    fun refreshWeatherList() : LiveData<Resource.Status>{
        val status = MutableLiveData<Resource.Status>()
        viewModelScope.launch {
            status.value = refreshWeatherListUseCase.refreshCurrentWeatherList().status ?: Resource.Status.LOADING
        }
        return status
    }
}