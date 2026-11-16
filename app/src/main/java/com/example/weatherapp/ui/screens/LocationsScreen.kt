package com.example.weatherapp.ui.screens

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.weatherapp.UpresIcon
import com.example.weatherapp.viewmodels.MainViewModel

@Composable
fun LocationsScreen(modifier: Modifier, viewModel: MainViewModel) {

    //Ask for location permissions (again)
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { results ->
        if (results.values.any { it }) {
            viewModel.refreshLocation() // call a function to get the current location for device
    }}
    LaunchedEffect(Unit) {
        launcher.launch(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        )
    }

    val locationFieldState = rememberTextFieldState()

    Column(
        modifier=modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){
        viewModel.weather?.let {
            Row(verticalAlignment = Alignment.CenterVertically){
                Text("${viewModel.weather!!.location.name}, ${viewModel.weather!!.location.region}")
                AsyncImage(
                    model = "https:${UpresIcon(viewModel.weather!!.current.condition.icon)}",
                    modifier = Modifier.size(80.dp),
                    contentDescription = viewModel.weather!!.current.condition.text
                )
                Text("${viewModel.weather!!.current.feelsLikeC}\u00B0C")
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                state = locationFieldState,
                lineLimits = TextFieldLineLimits.SingleLine,
                label = {Text("Enter Location")}
            )
            Button(
                onClick={
                    viewModel.savedLocations.add(locationFieldState.text.toString())
                }
            ){
                Text("Add")
            }
        }
        Button(
            onClick={
                viewModel.getWeather("${viewModel.location!!.latitude},${viewModel.location!!.longitude}")
            },
            enabled = viewModel.location != null
        ){
            Text("Current Location")
        }
        LazyColumn() {
            items(viewModel.savedLocations) { savedLocation ->
                Button(
                    onClick={
                        viewModel.getWeather(savedLocation)
                    }
                ){
                    Text(savedLocation)
                }
            }
        }
    }

}