package com.jvmori.openweather.forecast.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.jvmori.openweather.common.data.network.Resource
import com.jvmori.openweather.currentWeather.domain.entities.Coordinates
import com.jvmori.openweather.forecast.domain.entities.WeatherDetailsEntity
import com.jvmori.openweather.forecast.domain.usecases.FetchDetailsUseCase
import com.jvmori.openweather.util.TestCoroutineRule
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailsViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var forecastObserver: Observer<Resource<WeatherDetailsEntity>>

    @Mock
    private lateinit var useCase: FetchDetailsUseCase

    private lateinit var viewModel: DetailsViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = DetailsViewModel(useCase)
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            val inputData = WeatherDetailsEntity(0, 0, 0.00, listOf())
            val success = Resource.success(inputData)
            val coordinates = Coordinates()
            Mockito.doReturn(flowOf(success))
                .`when`(useCase)
                .fetchForecast(coordinates)

            viewModel.fetchWeatherDetails(coordinates)
            viewModel.weatherDetails.observeForever(forecastObserver)

            Mockito.verify(forecastObserver).onChanged(success)
            viewModel.weatherDetails.removeObserver(forecastObserver)
        }
    }

    @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        testCoroutineRule.runBlockingTest {
            val error: Resource<WeatherDetailsEntity> = Resource.networkError(null, "")
            val coordinates = Coordinates()
            Mockito.doReturn(flowOf(error))
                .`when`(useCase)
                .fetchForecast(coordinates)

            viewModel.fetchWeatherDetails(coordinates)
            viewModel.weatherDetails.observeForever(forecastObserver)

            Mockito.verify(forecastObserver).onChanged(error)
            viewModel.weatherDetails.removeObserver(forecastObserver)
        }
    }
}