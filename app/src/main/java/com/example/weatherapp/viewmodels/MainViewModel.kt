package com.example.weatherapp.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.WeatherApi
import com.example.weatherapp.data.dao.Weather
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Locale

class MainViewModel : ViewModel() {
    //Properties
    var message by mutableStateOf<String>("Initial Message")
    var weather by mutableStateOf<Weather?>(null)
    var currentLocale by mutableStateOf<Locale>(Locale.getDefault()) //I mean, it MIGHT change

    private val api = Retrofit.Builder()
        .baseUrl("https://api.weatherapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherApi::class.java)

    init{}

    //Functions
    fun getWeather(city: String) {
        viewModelScope.launch {
            weather = api.getWeather(city);
        }
    }
}