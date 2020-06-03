package com.jvmori.openweather.currentWeather.presentation.di

import com.jvmori.openweather.common.data.WeatherDatabase
import com.jvmori.openweather.currentWeather.data.local.CurrentWeatherDao
import com.jvmori.openweather.currentWeather.data.local.CurrentWeatherLocalDataSourceImpl
import com.jvmori.openweather.currentWeather.data.network.CurrentWeatherApi
import com.jvmori.openweather.currentWeather.data.network.CurrentWeatherRemoteDataSourceImpl
import com.jvmori.openweather.currentWeather.data.repositories.CurrentWeatherRepositoryImpl
import com.jvmori.openweather.currentWeather.domain.repositories.CurrentWeatherLocalDataSource
import com.jvmori.openweather.currentWeather.domain.repositories.CurrentWeatherRemoteDataSource
import com.jvmori.openweather.currentWeather.domain.repositories.CurrentWeatherRepository
import com.jvmori.openweather.currentWeather.presentation.viewmodels.CurrentWeatherViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val currentWeatherModule = module {
    single<CurrentWeatherDao> { (get() as WeatherDatabase).currentWeatherDao() }
    single<CurrentWeatherApi> { (get() as Retrofit).create(CurrentWeatherApi::class.java) }
    single<CurrentWeatherLocalDataSource> { CurrentWeatherLocalDataSourceImpl(dao = get()) }
    single<CurrentWeatherRemoteDataSource> { CurrentWeatherRemoteDataSourceImpl(weatherApi = get()) }
    single<CurrentWeatherRepository> { CurrentWeatherRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }
    viewModel { CurrentWeatherViewModel(repository = get()) }
}