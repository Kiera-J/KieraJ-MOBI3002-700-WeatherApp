package com.example.weatherapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.weatherapp.ui.components.CurrentDay
import com.example.weatherapp.viewmodels.MainViewModel

@Composable
fun TodayScreen(modifier: Modifier, viewModel: MainViewModel){
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CurrentDay()
        Button(onClick = {viewModel.message = "Today Message"}) { }
        Text(viewModel.message);
    }
}