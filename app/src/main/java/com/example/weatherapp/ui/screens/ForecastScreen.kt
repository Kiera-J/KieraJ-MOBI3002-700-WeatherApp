package com.example.weatherapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.models.WeatherOld
import com.example.weatherapp.viewmodels.MainViewModel
import java.util.Date

@Composable
fun ForecastScreen(modifier: Modifier, viewModel: MainViewModel) {
    val weatherOlds = listOf<WeatherOld>( //TODO: retrieve forecast data from API instead of hardcoding
        WeatherOld(
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
        WeatherOld(
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
        WeatherOld(
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

    val nbsp = '\u00A0' //We're using this because our misc info at the bottom is all one line
    //And we don't want the sentences to be separated

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(weatherOlds){ weatherOld ->

            val dayOfWeek: String = when(weatherOld.date.day){ // date.day is deprecated, but I don't care enough to refactor to use Calendar
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
            val month: String = when(weatherOld.date.month){
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


            Spacer(Modifier.height(25.dp))

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text="$dayOfWeek, $month ${weatherOld.date.date}",
                    fontWeight = FontWeight.Medium
                )
                Image( //TODO: Implement conditional logic based on weather data
                    painter = painterResource(R.drawable.cloud),
                    modifier = Modifier.size(80.dp),
                    contentDescription = "Cloud",
                )
                Text(
                    text="${weatherOld.tempHigh}\u00B0C High, ${weatherOld.tempLow}\u00B0C Low",
                    fontSize = 18.sp
                )
                Text( //TODO: Conditional logic, determine relevant fields to include based on weather data
                    text="CLOUD/SKY STATEMENT. " +
                            "Chance of rain$nbsp${"%.0f".format(weatherOld.pop * 100)}%. " +
                            "Amount$nbsp${weatherOld.rainMm}mm. " +
                            "Maximum winds$nbsp${weatherOld.windSpeed}kph. " +
                            "Humidity$nbsp${"%.2f".format(weatherOld.humidity*100)}%",
                    modifier=Modifier
                        .padding(10.dp, 5.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                )
            }

            Spacer(Modifier.height(25.dp))
            HorizontalDivider(thickness = 2.dp)
        }
    }
    Button(onClick = {viewModel.message = "Forecast Message"}) { }
    Text(viewModel.message)
}