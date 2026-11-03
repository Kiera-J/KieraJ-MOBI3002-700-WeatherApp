package com.example.weatherapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.weatherapp.ConvertDate
import com.example.weatherapp.UpresIcon
import com.example.weatherapp.viewmodels.MainViewModel
import java.util.Calendar

@Composable
fun ForecastScreen(modifier: Modifier, viewModel: MainViewModel) {
    val weather = viewModel.weather
    val cal = Calendar.Builder()

    val nbsp = '\u00A0' //We're using this because our misc info at the bottom is all one line
    //And we don't want the sentences to be separated

    weather?.let {


        LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {
            items(weather.forecast.forecastday) { forecastday ->

                //Set date values for current day
                val ymd = ConvertDate(forecastday.date)
                cal.setDate(ymd[0], ymd[1], ymd[2])
                val date = cal.build()

                //Get higher-res version of today's icon
                val newLink = UpresIcon(forecastday.day.condition.icon)

                Spacer(Modifier.height(25.dp))

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text =
                            "${date.getDisplayName(
                                Calendar.DAY_OF_WEEK,
                                Calendar.SHORT_STANDALONE,
                                viewModel.currentLocale
                            )}," +
                            " ${date.getDisplayName(
                                Calendar.MONTH, 
                                Calendar.LONG_STANDALONE,
                                viewModel.currentLocale)}" +
                            " ${date.get(Calendar.DAY_OF_MONTH)}" +
                            " ${date.get(Calendar.YEAR)}",
                        fontWeight = FontWeight.Medium
                    )
                    AsyncImage(
                        model = "https:${newLink}",
                        modifier = Modifier.size(80.dp),
                        contentDescription = forecastday.day.condition.text
                    )
                    Text(
                        text = "${forecastday.day.maxtempC}\u00B0C High, ${forecastday.day.mintempC}\u00B0C Low",
                        fontSize = 18.sp
                    )
                    Text(
                        text = "${forecastday.day.condition.text}. " +
                                "Chance of rain$nbsp${forecastday.day.dailyChanceOfRain}%. " +
                                "Amount$nbsp${forecastday.day.totalprecipMm}mm. " +
                                "Maximum${nbsp}winds$nbsp${forecastday.day.maxwindKph}kph. " +
                                "Humidity$nbsp${forecastday.day.avgHumidity}%",
                        modifier = Modifier
                            .padding(10.dp, 5.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp
                    )
                }

                Spacer(Modifier.height(25.dp))
                HorizontalDivider(thickness = 2.dp)
            }
        }

    }

}