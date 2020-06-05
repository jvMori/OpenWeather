package com.jvmori.openweather.forecast.presentation.ui


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.jvmori.openweather.R
import com.jvmori.openweather.common.data.network.Resource
import com.jvmori.openweather.common.presentation.ui.BindingFragment
import com.jvmori.openweather.currentWeather.data.ui.CurrentWeatherUI
import com.jvmori.openweather.currentWeather.domain.entities.Coordinates
import com.jvmori.openweather.databinding.FragmentDetailsBinding
import com.jvmori.openweather.forecast.data.ConditionData
import com.jvmori.openweather.forecast.domain.entities.WeatherDetailsEntity
import com.jvmori.openweather.forecast.presentation.viewmodels.DetailsViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DetailsFragment : BindingFragment(R.layout.fragment_details) {

    private lateinit var detailsBinding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()
    private val viewModel: DetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailsBinding = (binding as FragmentDetailsBinding)
    }

    override fun onStart() {
        super.onStart()
        getCurrentWeatherData()?.let {
            viewModel.fetchWeatherDetails(
                Coordinates(it.coordinates.lat, it.coordinates.lon)
            )
            detailsBinding.currentWeather = it
        }
        viewModel.weatherDetails.observe(this, Observer {
            when (it.status) {
                Resource.Status.LOADING -> showLoading()
                Resource.Status.SUCCESS -> showSuccess(it.data ?: WeatherDetailsEntity(0, 0, 0.00, listOf()))
                Resource.Status.ERROR, Resource.Status.NETWORKERROR -> showError()
            }
        })
    }

    private fun showLoading() {}
    private fun showSuccess(data: WeatherDetailsEntity) {
        detailsBinding.details = data
        detailsBinding.condition = ConditionData(getString(R.string.wind), resources.getDrawable(R.drawable.wind))
        detailsBinding.pressure = ConditionData(getString(R.string.pressure), resources.getDrawable(R.drawable.pressure))
        detailsBinding.humidity = ConditionData(getString(R.string.humidity), resources.getDrawable(R.drawable.humidity))
        detailsBinding.executePendingBindings()
    }

    private fun showError() {}

    private fun getCurrentWeatherData(): CurrentWeatherUI? {
        return try {
            args.currentWeather
        } catch (e: IllegalStateException) {
            //show error page
            null
        }
    }
}
