package com.jvmori.openweather.forecast.data.network.response


import com.google.gson.annotations.SerializedName

data class Daily(
    @SerializedName("dt")
    var dt: Int = 0,
    @SerializedName("temp")
    var temp: Temp = Temp(),
    @SerializedName("weather")
    var weather: List<Weather> = listOf()
)