package com.jvmori.openweather.forecast.presentation.di

import com.jvmori.openweather.forecast.data.network.ForecastApi
import com.jvmori.openweather.forecast.data.repositories.ForecastNetworkDataSourceImpl
import com.jvmori.openweather.forecast.data.repositories.ForecastRepositoryImpl
import com.jvmori.openweather.forecast.data.usecases.FetchDetailsUseCaseImpl
import com.jvmori.openweather.forecast.domain.repositories.ForecastNetworkDataSource
import com.jvmori.openweather.forecast.domain.repositories.ForecastRepository
import com.jvmori.openweather.forecast.domain.usecases.FetchDetailsUseCase
import com.jvmori.openweather.forecast.presentation.viewmodels.DetailsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val detailsModule = module {
    single<ForecastApi> { (get() as Retrofit).create(ForecastApi::class.java) }
    single<ForecastNetworkDataSource> { ForecastNetworkDataSourceImpl(api = get()) }
    single<ForecastRepository> {
        ForecastRepositoryImpl(
            dataSource = get()
        )
    }
    single<FetchDetailsUseCase> { FetchDetailsUseCaseImpl(repository = get()) }
    viewModel { DetailsViewModel(fetchDetailsUseCase = get()) }
}