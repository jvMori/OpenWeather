package com.jvmori.openweather.currentWeather.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.jvmori.openweather.R
import com.jvmori.openweather.common.data.Resource
import com.jvmori.openweather.common.presentation.ui.BindingFragment
import com.jvmori.openweather.currentWeather.presentation.viewmodels.CurrentWeatherViewModel
import com.jvmori.openweather.databinding.FragmentCurrentWeatherBinding
import kotlinx.android.synthetic.main.fragment_current_weather.*
import org.koin.android.viewmodel.ext.android.viewModel


class CurrentWeatherFragment : BindingFragment(R.layout.fragment_current_weather) {

    private lateinit var currentWeatherBinding: FragmentCurrentWeatherBinding
    private val viewModel: CurrentWeatherViewModel by viewModel()
    private lateinit var adapter: WeatherAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentWeatherBinding = (binding as FragmentCurrentWeatherBinding)
        adapter = WeatherAdapter.initGridAdapter(
            currentWeatherBinding.weatherList,
            this.requireContext(),
            listOf()
        )
    }

    override fun onStart() {
        super.onStart()
        observeWeatherList()
        setupOnWeatherClickListeners()
        swipeToRefreshListener()
    }

    private fun observeWeatherList() {
        viewModel.fetchWeather().observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    adapter.submitList(it.data ?: listOf())
                }
            }
        })
    }

    private fun setupOnWeatherClickListeners() {
        adapter.apply {
            onAddButtonClickListener = {
                viewModel.addNewWeather("Katowice")
            }
            onWeatherClickListener = {

            }
        }
    }

    private fun swipeToRefreshListener() {
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshWeatherList().observe(this, Observer {
                when (it) {
                    Resource.Status.SUCCESS -> {
                        swipeRefreshLayout.isRefreshing = false
                        showToast(R.string.weather_success_refresh)
                    }
                    Resource.Status.ERROR, Resource.Status.NETWORKERROR -> {
                        swipeRefreshLayout.isRefreshing = false
                        showToast(R.string.weather_failed_refresh)
                    }
                }
            })
        }
    }

    private fun showToast(message: Int) {
        Toast.makeText(
            this.requireContext(),
            getString(message),
            Toast.LENGTH_SHORT
        ).show()
    }
}
