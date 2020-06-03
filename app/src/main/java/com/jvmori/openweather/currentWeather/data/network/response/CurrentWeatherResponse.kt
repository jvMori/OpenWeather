package com.jvmori.openweather.currentWeather.data.network.response

import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    @SerializedName("coord")
    var coordinates: Coord = Coord(),
    @SerializedName("id")
    var weatherId: Int = 0,
    @SerializedName("main")
    var main: Main = Main(),
    @SerializedName("name")
    var cityName: String = "",
    @SerializedName("weather")
    var weather: List<Weather> = listOf()
)