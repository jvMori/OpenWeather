package com.jvmori.openweather.currentWeather.domain.entities

data class CurrentWeatherEntity(
    val city: String,
    val iconUrl: String,
    val temperature: Double
)