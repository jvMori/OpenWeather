package com.jvmori.openweather.forecast.domain.entities

data class DetailWeatherEntity(
    val pressure: Int,
    val humidity: Int,
    val windSpeed: Double,
    val dailyForecast: List<Forecast>
)