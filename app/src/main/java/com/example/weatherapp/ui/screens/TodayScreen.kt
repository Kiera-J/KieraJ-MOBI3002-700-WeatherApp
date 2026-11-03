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
import coil3.compose.AsyncImage
import com.example.weatherapp.R
import com.example.weatherapp.UpresIcon
import com.example.weatherapp.viewmodels.MainViewModel

@Composable
fun TodayScreen(modifier: Modifier, viewModel: MainViewModel){

    val weather = viewModel.weather

    val textSize = 18.sp

    weather?.let{

        val current = weather.current //This is just to make all the references shorter
        val newLink = UpresIcon(current.condition.icon)


        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            AsyncImage(
                model = "https:${newLink}",
                modifier = Modifier.size(120.dp),
                contentDescription = current.condition.text,
            )

            Text(
                text=current.condition.text,
                fontSize = textSize
            )

            Text(
                text="${current.tempC}\u00B0C", // \u00B0 is degree symbol
                fontSize = textSize * 2
            )

            Text(
                text="Feels like ${current.feelsLikeC}\u00B0C",
                fontSize = textSize
            )

            Text(  //TODO: conditional logic for wind direction based on data (convert number to North, East, South, West)
                text="Wind ${current.windDir} ${current.windKph} kph",
                fontSize = textSize
            )

        }
    }

}