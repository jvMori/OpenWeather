package com.jvmori.openweather.common.data

import com.jvmori.openweather.currentWeather.data.local.CurrentWeatherData
import com.jvmori.openweather.currentWeather.data.network.response.CurrentWeatherResponse
import com.jvmori.openweather.currentWeather.data.ui.CoordinatesUI
import com.jvmori.openweather.currentWeather.data.ui.CurrentWeatherUI
import com.jvmori.openweather.currentWeather.domain.entities.Coordinates
import com.jvmori.openweather.currentWeather.domain.entities.CurrentWeatherEntity

fun CurrentWeatherEntity.mapToUI(): CurrentWeatherUI = CurrentWeatherUI(
    CoordinatesUI(this.coordinates.lat, this.coordinates.lon),
    this.city,
    this.condition,
    this.iconUrl,
    this.temperature
)


fun CurrentWeatherResponse.mapToLocal(): CurrentWeatherData = CurrentWeatherData(
    this.weatherId,
    this.cityName,
    this.coordinates.lat,
    this.coordinates.lon,
    this.weather[0].description,
    this.weather[0].icon,
    this.main.temp
)

fun CurrentWeatherData.mapToEntity(): CurrentWeatherEntity = CurrentWeatherEntity(
    Coordinates(this.latitude, this.longitude),
    this.city,
    this.condition,
    this.iconUrl,
    this.temperature.toInt()
)