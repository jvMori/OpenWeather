package com.jvmori.openweather.currentWeather.data.repositories

import com.jvmori.openweather.common.data.Resource
import com.jvmori.openweather.common.data.handleError
import com.jvmori.openweather.currentWeather.data.local.CurrentWeatherData
import com.jvmori.openweather.currentWeather.data.network.response.CurrentWeatherResponse
import com.jvmori.openweather.currentWeather.domain.entities.CurrentWeatherEntity
import com.jvmori.openweather.currentWeather.domain.repositories.CurrentWeatherLocalDataSource
import com.jvmori.openweather.currentWeather.domain.repositories.CurrentWeatherRemoteDataSource
import com.jvmori.openweather.currentWeather.domain.repositories.CurrentWeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class CurrentWeatherRepositoryImpl(
    private val localDataSource: CurrentWeatherLocalDataSource,
    private val remoteDataSource: CurrentWeatherRemoteDataSource
) : CurrentWeatherRepository {

    private val defaultCities = listOf("Gdańsk", "Warszawa", "Kraków", "Wrocław", "Łódź")

    override suspend fun fetchNewWeather(city: String): Resource<String> {
        return try {
            fetchFromNetworkAndSave(city)
            Resource.success("")
        } catch (e: Exception) {
            handleError(e)
        }
    }

    override fun fetchAllWeather(): Flow<Resource<List<CurrentWeatherEntity>>> {
        return try {
            localDataSource.fetchAllWeather().map { weatherList ->
                if (weatherList.isEmpty()){
                    defaultCities.forEach { city ->
                        fetchFromNetworkAndSave(city)
                    }
                }
                Resource.success(mapLocalListToEntity(weatherList))
            }
        } catch (e: Exception) {
            flowOf(handleError(e))
        }
    }

    override suspend fun refreshCurrentWeatherList(): Resource<String> {
        return try {
            localDataSource.fetchAllWeather().map {
                it.forEach { weather ->
                    fetchFromNetworkAndSave(weather.city)
                }
            }
            Resource.success("")
        } catch (e: Exception) {
            handleError(e)
        }
    }

    private suspend fun fetchFromNetworkAndSave(city: String) {
        val remote = remoteDataSource.fetchCurrentWeather(city)
        val mapped = mapRemoteToLocal(remote)
        localDataSource.saveCurrentWeather(mapped)
    }

    private fun mapRemoteToLocal(remote: CurrentWeatherResponse): CurrentWeatherData {
        return CurrentWeatherData(
            remote.weatherId,
            remote.cityName,
            remote.weather[0].icon,
            remote.main.temp
        )
    }

    private fun mapLocalListToEntity(local: List<CurrentWeatherData>): List<CurrentWeatherEntity> {
        return local.map {
            CurrentWeatherEntity(it.city, it.iconUrl, it.temperature)
        }
    }

}