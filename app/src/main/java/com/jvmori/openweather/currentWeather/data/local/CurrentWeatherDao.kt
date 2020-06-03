package com.jvmori.openweather.currentWeather.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrentWeatherDao {
    @Query("Select * from current_weather ORDER BY timestamp ASC")
    fun getAllWeather(): Flow<List<CurrentWeatherData>>

    @Insert
    suspend fun insert(data: CurrentWeatherData)
}