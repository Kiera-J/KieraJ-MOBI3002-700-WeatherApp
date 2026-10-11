package com.example.weatherapp.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherapp.models.Weather
import com.example.weatherapp.ui.components.ForecastDay
import java.util.Date

@Composable
fun ForecastScreen(modifier: Modifier) {
    val weathers = listOf<Weather>( //TODO: retrieve forecast data from API instead of hardcoding
        Weather(
            tempCurrent = 5,
            tempHigh = 0, //Not relevant to today's instance weather? Maybe with a different UI with hourly forecast
            tempLow = 0,
            date = Date(2026,5,13),
            location = "Halifax",
            humidity = 0.5,
            pop = .5,
            rainMm = 0.0,
            snowMm = 0.0,
            clouds = 0.5,
            feelsLike = 2,
            windSpeed = 10,
            windDir = 90.0
        ),
        Weather(
            tempCurrent = 5,
            tempHigh = 0, //Not relevant to today's instance weather? Maybe with a different UI with hourly forecast
            tempLow = 0,
            date = Date(2026,5,14),
            location = "Halifax",
            humidity = 0.5,
            pop = .5,
            rainMm = 0.0,
            snowMm = 0.0,
            clouds = 0.5,
            feelsLike = 2,
            windSpeed = 10,
            windDir = 90.0
        ),
        Weather(
            tempCurrent = 5,
            tempHigh = 0, //Not relevant to today's instance weather? Maybe with a different UI with hourly forecast
            tempLow = 0,
            date = Date(2026,5,15),
            location = "Halifax",
            humidity = 0.5,
            pop = .5,
            rainMm = 0.0,
            snowMm = 0.0,
            clouds = 0.5,
            feelsLike = 2,
            windSpeed = 10,
            windDir = 90.0
        ),
    )

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(weathers){ weather ->
            Spacer(Modifier.height(25.dp))
            ForecastDay(weather)
            Spacer(Modifier.height(25.dp))
            HorizontalDivider(thickness = 2.dp)
        }
    }
}