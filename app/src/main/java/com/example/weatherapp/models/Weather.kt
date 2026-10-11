package com.example.weatherapp.models

import java.util.Date

//Should I have separate classes for forecasts and current data?
//Probably, not sure, this is a temp class just for visual demo anyway
data class Weather(
    val date: Date,
    val location: String,
    val humidity: Double,
    val pop: Double,
    val rainMm: Double,
    val snowMm: Double,
    val clouds: Double,
    val tempCurrent: Int,
    val tempHigh: Int,
    val tempLow: Int,
    val feelsLike: Int,
)