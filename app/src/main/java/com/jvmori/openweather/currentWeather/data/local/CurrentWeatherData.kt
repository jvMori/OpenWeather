package com.jvmori.openweather.currentWeather.data.local

data class CurrentWeatherData(
    var city: String,
    var iconUrl: String = "",
    var temperature: Float = 0.00f,
    var timestamp: Long = System.currentTimeMillis()
)