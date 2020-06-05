package com.jvmori.openweather.forecast.data.network.response


import com.google.gson.annotations.SerializedName

data class Temp(
    @SerializedName("day")
    var day: Double = 0.0,
    @SerializedName("night")
    var night: Double = 0.0
)