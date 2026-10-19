package com.example.weatherapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.models.Weather
import java.util.Date
import com.example.weatherapp.R

//Should this be the package structure? Is 'Components' what you'd call these?

@Composable
fun CurrentDay() {

    //TODO: Retrieve this data from actual API call instead of hardcoding
    val weather = Weather(
        tempCurrent = 5,
        tempHigh = 0, //Not relevant to today's instance weather? Maybe with a different UI with hourly forecast
        tempLow = 0,
        date = Date(),
        location = "Halifax",
        humidity = 0.5,
        pop = .5,
        rainMm = 0.0,
        snowMm = 0.0,
        clouds = 0.5,
        feelsLike = 2,
        windSpeed = 10,
        windDir = 90.0
    )

    val textSize = 18.sp

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Image( //TODO: Implement conditional logic based on current weather data
            painter = painterResource(R.drawable.cloud),
            modifier = Modifier.size(120.dp),
            contentDescription = "Cloud",
        )

        Text(  //TODO: conditional logic, same logic as that which selects the image
            text="Cloudy",
            fontSize = textSize
        )

        Text(
            text="${weather.tempCurrent}\u00B0C", // \u00B0 is degree symbol
            fontSize = textSize * 2
        )

        Text(
            text="Feels like ${weather.feelsLike}\u00B0C",
            fontSize = textSize
        )

        Text(  //TODO: conditional logic for wind direction based on data (convert number to North, East, South, West)
            text="Wind NW ${weather.windSpeed} kph",
            fontSize = textSize
        )
    }
}