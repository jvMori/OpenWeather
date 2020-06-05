package com.jvmori.openweather.currentWeather.data.repositories

import com.jvmori.openweather.common.data.Actions
import com.jvmori.openweather.common.data.Resource
import com.jvmori.openweather.common.data.handleError
import com.jvmori.openweather.currentWeather.data.local.CurrentWeatherData
import com.jvmori.openweather.currentWeather.data.network.response.CurrentWeatherResponse
import com.jvmori.openweather.currentWeather.domain.entities.Coordinates
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

    override suspend fun fetchNewWeather(city: String): Resource<Actions> {
        return try {
            fetchFromNetworkAndSave(city)
            Resource.success(Actions.AddNewWeather)
        } catch (e: Exception) {
            handleError(e, Actions.AddNewWeather)
        }
    }

    override fun observeAllWeather(): Flow<Resource<List<CurrentWeatherEntity>>> {
        return try {
            Resource.loading(null)
            localDataSource.observeAllWeather().map { weatherList ->
                Resource.success(mapLocalListToEntity(weatherList))
            }
        } catch (e: Exception) {
            flowOf(handleError(e))
        }
    }

    override suspend fun refreshCurrentWeatherList(): Resource<Actions> {
        return try {
            Resource.loading("")
            val cities: List<CurrentWeatherData> = localDataSource.getAllWeather()
            if (cities.isNotEmpty()) {
                cities.map {
                    fetchFromNetworkAndSave(it.city)
                }
            } else {
                defaultCities.map {
                    fetchFromNetworkAndSave(it)
                }
            }
            Resource.success(Actions.Refresh)
        } catch (e: Exception) {
            handleError(e, Actions.Refresh)
        }
    }

    override suspend fun initDefaultWeather(): Resource<Actions> {
        return try {
            fetchDefaultIfNeeded()
            Resource.success(Actions.InitDefaultWeather)
        } catch (e: Exception) {
            handleError(e, Actions.InitDefaultWeather)
        }
    }

    private suspend fun fetchDefaultIfNeeded() {
        val weatherList = localDataSource.getAllWeather()
        if (weatherList.isEmpty()) {
            defaultCities.forEach { city ->
                fetchFromNetworkAndSave(city)
            }
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
            remote.coordinates.lat,
            remote.coordinates.lon,
            remote.weather[0].description,
            remote.weather[0].icon,
            remote.main.temp
        )
    }

    private fun mapLocalListToEntity(local: List<CurrentWeatherData>): List<CurrentWeatherEntity> {
        return local.map {
            CurrentWeatherEntity(
                Coordinates(it.latitude, it.longitude),
                it.city,
                it.condition,
                it.iconUrl,
                it.temperature.toInt()
            )
        }
    }
}