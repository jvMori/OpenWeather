package com.jvmori.openweather.forecast.data.network

import com.jvmori.openweather.forecast.data.network.response.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApi {
    @GET("onecall")
    suspend fun fetchForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("exclude") exclude: String = "hourly,minutely"
    ): ForecastResponse
}