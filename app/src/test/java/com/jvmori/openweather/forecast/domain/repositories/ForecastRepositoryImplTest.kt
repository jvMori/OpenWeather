package com.jvmori.openweather.forecast.domain.repositories

import com.jvmori.openweather.common.data.Resource
import com.jvmori.openweather.common.data.mapToEntity
import com.jvmori.openweather.currentWeather.domain.entities.Coordinates
import com.jvmori.openweather.forecast.data.network.response.ForecastResponse
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.net.UnknownHostException

class ForecastRepositoryImplTest {
    private lateinit var repository: ForecastRepository

    @Mock
    private lateinit var dataSource: ForecastNetworkDataSource

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = ForecastRepositoryImpl(dataSource)
    }

    @Test
    fun `when fetching data successfully then return success`() {
        runBlockingTest {
            //Arrange
            val data = ForecastResponse()

            //Act
            Mockito.`when`(
                dataSource.fetchForecast(Coordinates())
            ).thenReturn(data)

            val result = repository.fetchForecast(Coordinates())

            //Assert
            assert(result.status == Resource.Status.SUCCESS)
            assert(result.data == data.mapToEntity())
        }
    }

    @Test
    fun `when fetching data and no network connection then return network error`() {
        runBlockingTest {

            //Act
            Mockito.`when`(
                dataSource.fetchForecast(Coordinates())
            ).thenAnswer {
                throw UnknownHostException()
            }

            val result = repository.fetchForecast(Coordinates())

            //Assert
            assert(result.status == Resource.Status.NETWORKERROR)
            assert(result.data == null)
        }
    }
}