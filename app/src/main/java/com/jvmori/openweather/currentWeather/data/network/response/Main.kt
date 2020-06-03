package com.jvmori.openweather.currentWeather.data.network.response


import com.google.gson.annotations.SerializedName

data class Main(
    @SerializedName("temp")
    var temp: Double = 0.0
)