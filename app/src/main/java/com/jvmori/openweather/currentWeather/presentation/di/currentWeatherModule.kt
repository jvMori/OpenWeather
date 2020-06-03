package com.jvmori.openweather.currentWeather.presentation.di

import com.jvmori.openweather.currentWeather.data.local.CurrentWeatherLocalDataSourceImpl
import com.jvmori.openweather.currentWeather.data.network.CurrentWeatherRemoteDataSourceImpl
import com.jvmori.openweather.currentWeather.data.repositories.CurrentWeatherRepositoryImpl
import com.jvmori.openweather.currentWeather.domain.repositories.CurrentWeatherLocalDataSource
import com.jvmori.openweather.currentWeather.domain.repositories.CurrentWeatherRemoteDataSource
import com.jvmori.openweather.currentWeather.domain.repositories.CurrentWeatherRepository
import com.jvmori.openweather.currentWeather.presentation.viewmodels.CurrentWeatherViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val currentWeatherModule = module {
    single<CurrentWeatherLocalDataSource> { CurrentWeatherLocalDataSourceImpl() }
    single<CurrentWeatherRemoteDataSource> { CurrentWeatherRemoteDataSourceImpl() }
    single<CurrentWeatherRepository> { CurrentWeatherRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }
    viewModel { CurrentWeatherViewModel(repository = get()) }
}