package com.jvmori.openweather.forecast.presentation.di

import com.jvmori.openweather.forecast.data.network.ForecastApi
import com.jvmori.openweather.forecast.data.repositories.ForecastNetworkDataSourceImpl
import com.jvmori.openweather.forecast.domain.repositories.ForecastNetworkDataSource
import com.jvmori.openweather.forecast.domain.repositories.ForecastRepository
import com.jvmori.openweather.forecast.domain.repositories.ForecastRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val detailsModule = module {
    single<ForecastApi> { (get() as Retrofit).create(ForecastApi::class.java) }
    single<ForecastNetworkDataSource> { ForecastNetworkDataSourceImpl(api = get()) }
    single<ForecastRepository> { ForecastRepositoryImpl(dataSource = get()) }
}