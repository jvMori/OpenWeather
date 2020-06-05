package com.jvmori.openweather.forecast.data.network.response


import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @SerializedName("current")
    var current: Current = Current(),
    @SerializedName("daily")
    var daily: List<Daily> = listOf()
)