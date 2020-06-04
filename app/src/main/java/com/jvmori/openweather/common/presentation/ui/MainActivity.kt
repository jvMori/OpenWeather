package com.jvmori.openweather.common.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.jvmori.openweather.R
import com.jvmori.openweather.common.data.Resource
import com.jvmori.openweather.currentWeather.presentation.viewmodels.CurrentWeatherViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val weatherViewModel : CurrentWeatherViewModel by viewModel<CurrentWeatherViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
