package com.jvmori.openweather.forecast.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jvmori.openweather.common.data.network.Resource
import com.jvmori.openweather.currentWeather.domain.entities.Coordinates
import com.jvmori.openweather.forecast.domain.entities.WeatherDetailsEntity
import com.jvmori.openweather.forecast.domain.usecases.FetchDetailsUseCase
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val fetchDetailsUseCase: FetchDetailsUseCase
) : ViewModel() {

    private val _details = MutableLiveData<Resource<WeatherDetailsEntity>>()
    val weatherDetails : LiveData<Resource<WeatherDetailsEntity>> = _details

    fun fetchWeatherDetails(coordinates: Coordinates){
        viewModelScope.launch {
            _details.value = Resource.loading(null)
            _details.value = fetchDetailsUseCase.fetchForecast(coordinates)
        }
    }
}