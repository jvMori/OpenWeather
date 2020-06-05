package com.jvmori.openweather.currentWeather.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.jvmori.openweather.common.data.local.WeatherDatabase
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidApplication
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject

class CurrentWeatherDaoTest : KoinTest {
    private val dao: CurrentWeatherDao by inject()
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun before() {
        loadKoinModules(databaseTestModule)
    }

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun when_database_is_empty_return_np_results() {
        runBlocking {
            //Act
            dao.observeAllWeather().map {
                //Arrange
                assertTrue(it.isEmpty())
            }
        }
    }

    @Test
    fun when_saving_data_successfully_then_get_data_returns_success() {
        runBlocking {
            //Arrange
            val weather = CurrentWeatherData(1, "", 0.00, 0.00,"")

            //Act
            dao.insert(weather)
            dao.observeAllWeather().map {

                //Arrange
                assertTrue(it.isNotEmpty())
                assertTrue(it.contains(weather))
            }
        }
    }

    private val databaseTestModule = module {
        single(override = true) {
            Room.inMemoryDatabaseBuilder(androidApplication().applicationContext, WeatherDatabase::class.java)
                .setTransactionExecutor(testDispatcher.asExecutor())
                .setQueryExecutor(testDispatcher.asExecutor())
                .build()
        }
    }
}