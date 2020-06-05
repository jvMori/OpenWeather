package com.jvmori.openweather.forecast.data.network.response


import com.google.gson.annotations.SerializedName

data class Current(
    @SerializedName("dt")
    var dt: Int = 0,
    @SerializedName("humidity")
    var humidity: Int = 0,
    @SerializedName("pressure")
    var pressure: Int = 0,
    @SerializedName("wind_speed")
    var windSpeed: Double = 0.0
)