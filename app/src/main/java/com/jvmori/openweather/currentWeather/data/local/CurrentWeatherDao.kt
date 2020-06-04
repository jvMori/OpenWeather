package com.jvmori.openweather.currentWeather.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrentWeatherDao {
    @Query("Select * from current_weather ORDER BY city_name ASC")
    fun observeAllWeather(): Flow<List<CurrentWeatherData>>

    @Query("Select * from current_weather ORDER BY city_name ASC")
    suspend fun getAllWeather(): List<CurrentWeatherData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: CurrentWeatherData)
}