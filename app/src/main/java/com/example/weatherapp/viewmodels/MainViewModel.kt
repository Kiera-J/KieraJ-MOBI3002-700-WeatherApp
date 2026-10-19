package com.example.weatherapp.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    //Properties
    public var message by mutableStateOf<String>("Initial Message")

    init{}

    //Functions

}