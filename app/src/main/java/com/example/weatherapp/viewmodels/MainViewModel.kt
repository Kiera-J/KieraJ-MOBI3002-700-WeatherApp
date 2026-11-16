package com.example.weatherapp.viewmodels

import android.annotation.SuppressLint
import android.app.Application
import android.location.Location
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.WeatherApi
import com.example.weatherapp.data.dao.Weather
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Locale

class MainViewModel(app: Application) : AndroidViewModel(app) {
    var weather by mutableStateOf<Weather?>(null)
    var currentLocale by mutableStateOf<Locale>(Locale.getDefault()) //I mean, it MIGHT change
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(app)
    var location by mutableStateOf<Location?>(null)

    var locationList by mutableStateOf<List<String>>(listOf())

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
    @SuppressLint("MissingPermission") // silence warning
    fun refreshLocation() {
        fusedLocationClient
            .getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
            .addOnSuccessListener { result ->
                location = result
                val latlng = "lat: ${location?.latitude}, lng: ${location?.longitude}"
                Log.i("MainViewModel", "Phone is location at $latlng.")
            }
    }
}