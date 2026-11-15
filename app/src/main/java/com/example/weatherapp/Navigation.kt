package com.example.weatherapp

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrightnessLow
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.ui.screens.ForecastScreen
import com.example.weatherapp.ui.screens.TodayScreen
import com.example.weatherapp.viewmodels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(){

    val navController = rememberNavController()
    var selectedIndex by remember { mutableIntStateOf(0) } // default set to 0
    val viewModel : MainViewModel = viewModel()

    // Begin: Ask permission to get device location.
    // Note: If permission is previously granted, the dialog won't appear and refreshLocation() will get called.
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { results ->
        if (results.values.any { it }) {
            viewModel.refreshLocation() // call a function to get the current location for device
        }
    }

    LaunchedEffect(Unit) {
        launcher.launch(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        )
    }

    viewModel.location?.let {
        viewModel.getWeather("${viewModel.location!!.latitude},${viewModel.location!!.longitude}")
    }
    viewModel.weather?.let {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text("${viewModel.weather!!.location.name}, ${viewModel.weather!!.location.region}")
                    }
                )
            },
            bottomBar = {
                NavigationBar(windowInsets = NavigationBarDefaults.windowInsets)
                {
                    NavigationBarItem(
                        icon = { Icon(imageVector = Icons.Default.BrightnessLow, contentDescription = "Search") },
                        label = { Text("Today") },
                        selected = selectedIndex == 0,
                        onClick = {
                            selectedIndex = 0
                            navController.navigate(route="today"){
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon(imageVector = Icons.Default.CalendarMonth, contentDescription = "Search") },
                        label = { Text("Daily Forecast") },
                        selected = selectedIndex == 1,
                        onClick = {
                            selectedIndex = 1
                            navController.navigate(route="forecast"){
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "today",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(route = "today") {
                    TodayScreen(Modifier, viewModel)
                }
                composable(route = "forecast") {
                    ForecastScreen(Modifier, viewModel)
                }
            }
        }
    }
}