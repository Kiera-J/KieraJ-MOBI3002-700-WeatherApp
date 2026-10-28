package com.example.weatherapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.models.WeatherOld
import com.example.weatherapp.viewmodels.MainViewModel
import java.util.Calendar
import java.util.Date

@Composable
fun TodayScreen(modifier: Modifier, viewModel: MainViewModel){

//    val weather = viewModel.weather


    //TODO: Retrieve this data from actual API call instead of hardcoding
    val weatherOld = WeatherOld(
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

//    weather?.let{

        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

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
                text="${weatherOld.tempCurrent}\u00B0C", // \u00B0 is degree symbol
                fontSize = textSize * 2
            )

            Text(
                text="Feels like ${weatherOld.feelsLike}\u00B0C",
                fontSize = textSize
            )

            Text(  //TODO: conditional logic for wind direction based on data (convert number to North, East, South, West)
                text="Wind NW ${weatherOld.windSpeed} kph",
                fontSize = textSize
            )

        }
//    }

}