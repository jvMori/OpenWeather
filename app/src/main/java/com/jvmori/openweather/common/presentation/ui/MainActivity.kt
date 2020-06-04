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

    private val viewModel: CurrentWeatherViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchWeather().observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    Log.i("WEATHER", it.data.toString())
                    helloWorld.text = it.data.toString()
                }
                Resource.Status.ERROR -> {
                    Log.i("WEATHER", it.message ?: "ERROR")
                    helloWorld.text = it.message ?: "ERROR"
                }
            }
        })
        button.setOnClickListener {
            viewModel.addNewWeather("Gdynia")
        }
        viewModel.status.observe(this, Observer {
            Snackbar.make(findViewById(R.id.button), it.toString(), Snackbar.LENGTH_LONG).show()
        })
    }

}
