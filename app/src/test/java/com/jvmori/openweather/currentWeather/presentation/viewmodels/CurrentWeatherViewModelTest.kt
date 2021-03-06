package com.jvmori.openweather.currentWeather.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.jvmori.openweather.common.data.network.Resource
import com.jvmori.openweather.currentWeather.domain.entities.Coordinates
import com.jvmori.openweather.currentWeather.domain.entities.CurrentWeatherEntity
import com.jvmori.openweather.currentWeather.domain.usecases.FetchNewWeatherUseCase
import com.jvmori.openweather.currentWeather.domain.usecases.FetchWeatherListUseCase
import com.jvmori.openweather.currentWeather.domain.usecases.InitDefaultWeatherUseCase
import com.jvmori.openweather.currentWeather.domain.usecases.RefreshWeatherListUseCase
import com.jvmori.openweather.util.TestCoroutineRule
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class CurrentWeatherViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var weatherObserver: Observer<Resource<List<CurrentWeatherEntity>>>

    @Mock
    private lateinit var fetchWeatherListUseCase: FetchWeatherListUseCase

    @Mock
    private lateinit var fetchNewWeatherUseCase: FetchNewWeatherUseCase

    @Mock
    private lateinit var refreshWeatherListUseCase: RefreshWeatherListUseCase

    @Mock
    private lateinit var initDefaultWeatherUseCase: InitDefaultWeatherUseCase

    private lateinit var viewModel: CurrentWeatherViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = CurrentWeatherViewModel(
            fetchWeatherListUseCase,
            fetchNewWeatherUseCase,
            refreshWeatherListUseCase,
            initDefaultWeatherUseCase
        )
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            val inputData = listOf(CurrentWeatherEntity(Coordinates(), "", "", "", 0))
            val success = Resource.success(inputData)
            doReturn(flowOf(success))
                .`when`(fetchWeatherListUseCase)
                .fetchAllWeather()

            viewModel.fetchWeather().observeForever(weatherObserver)

            verify(fetchWeatherListUseCase).fetchAllWeather()
            verify(weatherObserver).onChanged(success)
            viewModel.fetchWeather().removeObserver(weatherObserver)
        }
    }

    @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        testCoroutineRule.runBlockingTest {
            val error: Resource<List<CurrentWeatherEntity>> = Resource.networkError(null, "")
            doReturn(flowOf(error))
                .`when`(fetchWeatherListUseCase)
                .fetchAllWeather()

            viewModel.fetchWeather().observeForever(weatherObserver)

            verify(fetchWeatherListUseCase).fetchAllWeather()
            verify(weatherObserver).onChanged(error)
            viewModel.fetchWeather().removeObserver(weatherObserver)
        }
    }
}