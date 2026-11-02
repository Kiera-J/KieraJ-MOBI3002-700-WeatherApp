package com.example.weatherapp.data

import com.example.weatherapp.data.dao.Weather
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {
    @GET("/v1/forecast.json")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("key") key: String = "",
        @Query("days") days: String = "5",
        @Query("aqi") aqi: String = "no",
        @Query("alerts") alerts: String = "no"
    ): Weather
}