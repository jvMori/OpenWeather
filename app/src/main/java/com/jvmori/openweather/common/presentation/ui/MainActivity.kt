package com.jvmori.openweather.common.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jvmori.openweather.R
import com.jvmori.openweather.currentWeather.presentation.viewmodels.CurrentWeatherViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val weatherViewModel : CurrentWeatherViewModel by viewModel<CurrentWeatherViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
