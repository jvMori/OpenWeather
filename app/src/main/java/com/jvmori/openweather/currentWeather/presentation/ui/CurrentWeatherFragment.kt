package com.jvmori.openweather.currentWeather.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.jvmori.openweather.R
import com.jvmori.openweather.common.data.Actions
import com.jvmori.openweather.common.data.Resource
import com.jvmori.openweather.common.presentation.ui.BindingFragment
import com.jvmori.openweather.currentWeather.presentation.viewmodels.CurrentWeatherViewModel
import com.jvmori.openweather.databinding.FragmentCurrentWeatherBinding
import kotlinx.android.synthetic.main.fragment_current_weather.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class CurrentWeatherFragment : BindingFragment(R.layout.fragment_current_weather) {

    private lateinit var currentWeatherBinding: FragmentCurrentWeatherBinding
    private val viewModel: CurrentWeatherViewModel by sharedViewModel<CurrentWeatherViewModel>()
    private lateinit var adapter: WeatherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.initDefaultWeather()
    }

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
        showFailMessageIfNeeded()
    }
    private fun observeWeatherList() {
        viewModel.fetchWeather().observe(this, Observer {
            when (it.status) {
                Resource.Status.LOADING -> showProgressBar()
                Resource.Status.SUCCESS -> {
                    adapter.submitList(it.data ?: listOf())
                    hideProgressBar()
                }
                Resource.Status.ERROR, Resource.Status.NETWORKERROR -> {
                    hideProgressBar()
                    showErrorMessage()
                }
            }
        })
    }

    private fun setupOnWeatherClickListeners() {
        adapter.apply {
            onAddButtonClickListener = {
                openDialog()
            }
            onWeatherClickListener = {

            }
        }
    }

    private fun openDialog(){
        findNavController().navigate(R.id.action_currentWeatherFragment_to_addNewCityDialog)
    }

    private fun swipeToRefreshListener() {
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshWeatherList()
        }
    }

    private fun showFailMessageIfNeeded() {
        viewModel.status.observe(this, Observer {
            when (it.data) {
                Actions.Refresh -> showRefreshStatus(it.status)
                Actions.InitDefaultWeather -> showInitWeatherStatus(it.status)
                Actions.AddNewWeather -> showAddNewWeatherStatus(it.status)
            }
        })
    }

    private fun showRefreshStatus(status: Resource.Status?) {
        when (status) {
            Resource.Status.SUCCESS -> {
                swipeRefreshLayout.isRefreshing = false
                showToast(R.string.weather_success_refresh)
            }
            Resource.Status.ERROR, Resource.Status.NETWORKERROR -> {
                swipeRefreshLayout.isRefreshing = false
                showToast(R.string.weather_failed_refresh)
            }
        }
    }

    private fun showInitWeatherStatus(status: Resource.Status?) {
        when (status){
            Resource.Status.LOADING -> showProgressBar()
            Resource.Status.ERROR, Resource.Status.NETWORKERROR -> showInitError()
        }
    }

    private fun showAddNewWeatherStatus(status: Resource.Status?) {

    }

    private fun showProgressBar() {
        currentWeatherBinding.info.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        currentWeatherBinding.info.visibility = View.GONE
    }

    private fun showErrorMessage() {
        Snackbar.make(this.requireView(), getString(R.string.read_weather_fail), Snackbar.LENGTH_LONG).show()
    }

    private fun showInitError(){

    }

    private fun showToast(message: Int) {
        Toast.makeText(
            this.requireContext(),
            getString(message),
            Toast.LENGTH_SHORT
        ).show()
    }
}
