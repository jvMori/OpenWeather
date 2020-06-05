package com.jvmori.openweather.forecast.domain.entities

data class Forecast(
    val currentTime: Long,
    val condition: String,
    val iconUrl: String,
    val temperature : Int
)