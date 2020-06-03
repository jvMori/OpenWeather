package com.jvmori.openweather.currentWeather.data.local

data class CurrentWeatherData(
    var weatherId: Int,
    var city: String,
    var iconUrl: String = "",
    var temperature: Double = 0.00,
    var timestamp: Long = System.currentTimeMillis()
)