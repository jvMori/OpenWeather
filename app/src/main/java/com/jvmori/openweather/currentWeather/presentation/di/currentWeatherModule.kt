package com.jvmori.openweather.currentWeather.presentation.di

import com.jvmori.openweather.common.data.WeatherDatabase
import com.jvmori.openweather.currentWeather.data.local.CurrentWeatherDao
import com.jvmori.openweather.currentWeather.data.local.CurrentWeatherLocalDataSourceImpl
import com.jvmori.openweather.currentWeather.data.network.CurrentWeatherApi
import com.jvmori.openweather.currentWeather.data.network.CurrentWeatherRemoteDataSourceImpl
import com.jvmori.openweather.currentWeather.data.repositories.CurrentWeatherRepositoryImpl
import com.jvmori.openweather.currentWeather.data.usecases.FetchNewWeatherUseCaseImpl
import com.jvmori.openweather.currentWeather.data.usecases.FetchWeatherListUseCaseImpl
import com.jvmori.openweather.currentWeather.data.usecases.InitDefaultWeatherUseCaseImpl
import com.jvmori.openweather.currentWeather.data.usecases.RefreshWeatherListUseCaseImpl
import com.jvmori.openweather.currentWeather.domain.repositories.CurrentWeatherLocalDataSource
import com.jvmori.openweather.currentWeather.domain.repositories.CurrentWeatherRemoteDataSource
import com.jvmori.openweather.currentWeather.domain.repositories.CurrentWeatherRepository
import com.jvmori.openweather.currentWeather.domain.usecases.FetchNewWeatherUseCase
import com.jvmori.openweather.currentWeather.domain.usecases.FetchWeatherListUseCase
import com.jvmori.openweather.currentWeather.domain.usecases.InitDefaultWeatherUseCase
import com.jvmori.openweather.currentWeather.domain.usecases.RefreshWeatherListUseCase
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
    single<FetchWeatherListUseCase> { FetchWeatherListUseCaseImpl(repository = get()) }
    single<FetchNewWeatherUseCase> { FetchNewWeatherUseCaseImpl(repository = get()) }
    single<RefreshWeatherListUseCase> { RefreshWeatherListUseCaseImpl(repository = get()) }
    single<InitDefaultWeatherUseCase> { InitDefaultWeatherUseCaseImpl(repository = get()) }
    viewModel {
        CurrentWeatherViewModel(
            fetchWeatherListUseCase = get(),
            addNewWeatherUseCase = get(),
            refreshWeatherListUseCase = get(),
            initDefaultWeatherUseCase = get()
        )
    }
}