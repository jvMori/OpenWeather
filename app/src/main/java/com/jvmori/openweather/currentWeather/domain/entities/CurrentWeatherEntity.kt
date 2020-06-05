package com.jvmori.openweather.currentWeather.domain.entities

data class CurrentWeatherEntity(
    var coordinates: Coordinates = Coordinates(),
    val city: String,
    val condition: String,
    val iconUrl: String,
    val temperature: Int
)