package com.jvmori.openweather.currentWeather.data.repositories

import com.jvmori.openweather.common.data.network.Resource
import com.jvmori.openweather.currentWeather.data.local.CurrentWeatherData
import com.jvmori.openweather.currentWeather.data.network.response.CurrentWeatherResponse
import com.jvmori.openweather.currentWeather.data.network.response.Weather
import com.jvmori.openweather.currentWeather.domain.entities.Coordinates
import com.jvmori.openweather.currentWeather.domain.entities.CurrentWeatherEntity
import com.jvmori.openweather.currentWeather.domain.repositories.CurrentWeatherLocalDataSource
import com.jvmori.openweather.currentWeather.domain.repositories.CurrentWeatherRemoteDataSource
import com.jvmori.openweather.currentWeather.domain.repositories.CurrentWeatherRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CurrentWeatherRepositoryImplTest {

    private lateinit var repository: CurrentWeatherRepository
    @Mock
    private lateinit var localDataSource: CurrentWeatherLocalDataSource
    @Mock
    private lateinit var remoteDataSource: CurrentWeatherRemoteDataSource

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = CurrentWeatherRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Test
    fun `when fetching all weather for the first time successfully then return default cities`() {
        //Arrange
        val cities = listOf("Gdańsk", "Warszawa")
        val localData = listOf(
            CurrentWeatherData(1, cities[0], 0.00, 0.00, ""),
            CurrentWeatherData(2, cities[1], 0.00, 0.00, "")
        )

        //Act
        Mockito.`when`(localDataSource.observeAllWeather()).thenReturn(
            flowOf(listOf())
        )

        repository.observeAllWeather().map {
            val city: List<CurrentWeatherEntity> = it.data ?: listOf(CurrentWeatherEntity(Coordinates(), "", "", "", 0))
            assert(it.status == Resource.success(localData))
            assert(city[0].city == cities[0])
        }
    }

    @Test
    fun `when fetching all saved weather successfully then return success`() {
        //Arrange
        val localData = listOf(CurrentWeatherData(1, "", 0.00, 0.00, ""))

        //Act
        Mockito.`when`(localDataSource.observeAllWeather()).thenReturn(
            flowOf(localData)
        )

        repository.observeAllWeather().map {
            //Arrange
            assert(it.status == Resource.success(localData))
        }
    }

    @Test
    fun `when fetching all saved weather not successfully then return error`() {
        //Act
        Mockito.`when`(localDataSource.observeAllWeather()).thenAnswer {
            throw Exception()
        }

        repository.observeAllWeather().map {
            //Arrange
            assert(it.status == Resource.Status.ERROR)
        }
    }

    @Test
    fun `when fetching new weather successfully then save locally and return success`() {
        runBlocking {
            //Arrange
            val remoteData = CurrentWeatherResponse(weather = listOf(Weather()))

            //Act
            Mockito.`when`(remoteDataSource.fetchCurrentWeather(Mockito.anyString())).thenReturn(
                remoteData
            )
            val result = repository.fetchNewWeather(Mockito.anyString())

            //Arrange
            assert(result.status == Resource.Status.SUCCESS)
        }
    }
}