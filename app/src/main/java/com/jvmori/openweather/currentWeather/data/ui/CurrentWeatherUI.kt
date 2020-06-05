package com.jvmori.openweather.currentWeather.data.ui

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrentWeatherUI(
    val coordinates: CoordinatesUI,
    val city: String,
    val condition: String,
    val iconUrl: String,
    val temperature: Int
) : Parcelable