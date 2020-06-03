package com.jvmori.openweather.currentWeather.presentation.di

import com.jvmori.openweather.currentWeather.data.repositories.CurrentWeatherRepositoryImpl
import com.jvmori.openweather.currentWeather.domain.repositories.CurrentWeatherRepository
import com.jvmori.openweather.currentWeather.presentation.viewmodels.CurrentWeatherViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val currentWeatherModule = module {
    single<CurrentWeatherRepository> { CurrentWeatherRepositoryImpl() }
    viewModel { CurrentWeatherViewModel(repository = get()) }
}