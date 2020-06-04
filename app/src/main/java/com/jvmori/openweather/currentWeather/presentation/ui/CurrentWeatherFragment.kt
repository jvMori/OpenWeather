package com.jvmori.openweather.currentWeather.presentation.ui

import android.os.Bundle
import android.view.View
import com.jvmori.openweather.R
import com.jvmori.openweather.common.presentation.ui.BindingFragment
import com.jvmori.openweather.databinding.FragmentCurrentWeatherBinding


class CurrentWeatherFragment : BindingFragment(R.layout.fragment_current_weather) {

    private lateinit var currentWeatherBinding: FragmentCurrentWeatherBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentWeatherBinding = (binding as FragmentCurrentWeatherBinding)
    }
}
