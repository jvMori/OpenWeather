package com.jvmori.openweather.forecast.presentation


import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.jvmori.openweather.R
import com.jvmori.openweather.common.presentation.ui.BindingFragment
import com.jvmori.openweather.currentWeather.data.ui.CoordinatesUI
import com.jvmori.openweather.currentWeather.data.ui.CurrentWeatherUI
import com.jvmori.openweather.databinding.FragmentDetailsBinding

class DetailsFragment : BindingFragment(R.layout.fragment_details) {

    private lateinit var detailsBinding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()
    private lateinit var currentWeather: CurrentWeatherUI

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailsBinding = (binding as FragmentDetailsBinding)
    }

    override fun onStart() {
        super.onStart()
        getCurrentWeatherData()
    }

    private fun getCurrentWeatherData() {
        try {
            currentWeather = args.currentWeather ?: CurrentWeatherUI(CoordinatesUI(), "", "", "", 0)
        } catch (e: IllegalStateException) {
            //show error page
        }
    }
}
