package com.jvmori.openweather.currentWeather.data.ui

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CoordinatesUI(
    var lat: Double = 0.00,
    var lon: Double = 0.00
) : Parcelable