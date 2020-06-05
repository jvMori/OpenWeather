package com.jvmori.openweather.forecast.data

data class ForecastUI(
    val day: String,
    val condition: String,
    val iconUrl: String,
    val temp : Int
)