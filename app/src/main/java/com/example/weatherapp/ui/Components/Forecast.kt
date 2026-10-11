package com.example.weatherapp.ui.Components

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
import com.example.weatherapp.R
import com.example.weatherapp.models.Weather

@Composable
fun Forecast(weather: Weather) {
    val dayOfWeek: String = when(weather.date.day){ // date.day is deprecated, but I don't care enough to refactor to use Calendar
        0 -> "Sun"
        1 -> "Mon"
        2 -> "Tue"
        3 -> "Wed"
        4 -> "Thu"
        5 -> "Fri"
        6 -> "Sat"
        else -> "???" //Should never happen
    }

    //There's probably a better way to do this string conversion but I don't care
    val month: String = when(weather.date.month){
        0 -> "Jan"
        1 -> "Feb"
        2 -> "Mar"
        3 -> "Apr"
        4 -> "May"
        5 -> "Jun"
        6 -> "Jul"
        7 -> "Aug"
        8 -> "Sep"
        9 -> "Oct"
        10 -> "Nov"
        11 -> "Dec"
        else -> "???"
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text="$dayOfWeek, $month, ${weather.date.date}"
        )
        Image( //TODO: Implement conditional logic based on weather data
            painter = painterResource(R.drawable.cloud),
            modifier = Modifier.size(80.dp),
            contentDescription = "Cloud",
        )
        Text(
            text="${weather.tempHigh}\u00B0C High, ${weather.tempLow}\u00B0C Low"
        )
        Text( //TODO: Conditional logic, determine relevant fields to include based on weather data
            text="CLOUD/SKY STATEMENT. Chance of rain ${weather.pop}. Amount ${weather.rainMm}. " +
                    "Maximum winds ${weather.windSpeed}kph. Humidity ${"%.2f".format(weather.humidity*100)}"
        )
    }
}