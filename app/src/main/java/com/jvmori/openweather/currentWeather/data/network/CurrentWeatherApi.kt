package com.jvmori.openweather.currentWeather.data.network

import com.jvmori.openweather.currentWeather.data.network.response.CurrentWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrentWeatherApi {
    @GET("weather")
    suspend fun fetchWeather(@Query("q") city: String): CurrentWeatherResponse
}