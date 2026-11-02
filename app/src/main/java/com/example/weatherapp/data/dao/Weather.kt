package com.example.weatherapp.data.dao

import com.google.gson.annotations.SerializedName

data class Weather(
    val location: Location,
    val current: Current,
    val forecast: Forecast,
)

data class Location(
    val name: String,
    val region: String,
    val country: String,
    @SerializedName("tz_id") val timezoneId: String,
    val lat: Float,
    val lon: Float,
    @SerializedName("localtime_epoch")val localtimeEpoch: Long,
    val localtime: String
)

data class Current (
    @SerializedName("last_updated") val lastUpdated: String,
    @SerializedName("last_updated_epoch") val lastUpdatedEpoch: Long,
    @SerializedName("temp_c") val tempC: Float,
    @SerializedName("temp_f") val tempF: Float,
    @SerializedName("feelslike_c") val feelsLikeC: Float,
    @SerializedName("feelslike_f") val feelsLikeF: Float,
    @SerializedName("windchill_c") val windchillC: Float,
    @SerializedName("windchill_f") val windchillF: Float,
    @SerializedName("heatindex_c") val heatIndexC: Float,
    @SerializedName("heatindex_f") val heatIndexF: Float,
    @SerializedName("dewpoint_c") val dewpointC: Float,
    @SerializedName("dewpoint_f") val dewpointF: Float,
    val condition: Condition,
    @SerializedName("wind_mph") val windMph: Float,
    @SerializedName("wind_kph") val windKph: Float,
    @SerializedName("wind_degree") val windDegree: Int,
    @SerializedName("wind_dir") val windDir: String,
    @SerializedName("pressure_mb") val pressureMb: Float,
    @SerializedName("pressure_in") val pressureIn: Float,
    @SerializedName("precip_mm") val precipMm: Float,
    @SerializedName("precip_in") val precipIn: Float,
    val humidity: Int,
    val cloud: Int,
    @SerializedName("is_day") val isDay: Int,
    @SerializedName("will_it_rain") val willItRain: Int,      //
    @SerializedName("chance_of_rain") val chanceOfRain: Int,    //Not in docs, but does show up in API call
    @SerializedName("will_it_snow") val willItSnow: Int,      //
    @SerializedName("chance_of_snow") val chanceOfSnow: Int,    //
    val uv: Float,
    @SerializedName("gust_mph") val gustMph: Float,
    @SerializedName("gust_kph") val gustKph: Float,
    @SerializedName("short_rad") val shortRad: Float,
    @SerializedName("diff_rad") val diffRad: Float,
    val dni: Float,
    val gti: Float,
)

data class Condition(
    val text: String,
    val icon: String,
    val code: Int,
)

data class Forecast(
    val forecastday: List<ForecastDay>
)

data class ForecastDay(
    val date: String,
    @SerializedName("date_epoch") val dateEpoch: Long,
    val day: Day,
    val hour: List<Hour>
)

data class Day(
    @SerializedName("maxtemp_c") val maxtempC: Float,
    @SerializedName("maxtemp_f") val maxtempF: Float,
    @SerializedName("mintemp_c") val mintempC: Float,
    @SerializedName("mintemp_f") val mintempF: Float,
    @SerializedName("avgtemp_c") val avgtempC: Float,
    @SerializedName("avgtemp_f") val avgtempF: Float,
    @SerializedName("maxwind_mph") val maxwindMph: Float,
    @SerializedName("maxwind_kph") val maxwindKph: Float,
    @SerializedName("totalprecip_mm") val totalprecipMm: Float,
    @SerializedName("totalprecip_in") val totalprecipIn: Float,
    @SerializedName("totalsnow_cm") val totalsnowCm: Float,
    @SerializedName("avgvis_km") val avgvisKm: Float,
    @SerializedName("avgvis_miles") val avgvisMiles: Float,
    @SerializedName("avghumidity") val avgHumidity: Float,
    val condition: Condition,
    val uv: Float,
    @SerializedName("daily_will_it_rain") val dailyWillItRain: Int,
    @SerializedName("daily_will_it_snow") val dailyWillItSnow: Int,
    @SerializedName("daily_chance_of_rain") val dailyChanceOfRain: Int,
    @SerializedName("daily_chance_of_snow") val dailyChanceOfSnow: Int,
)

data class Hour( //Yes, Current and hour are almost exactly the same
    val time: String,
    @SerializedName("time_epoch") val timeEpoch: Long,
    @SerializedName("temp_c") val tempC: Float,
    @SerializedName("temp_f") val tempF: Float,
    @SerializedName("feelslike_c") val feelsLikeC: Float,
    @SerializedName("feelslike_f") val feelsLikeF: Float,
    @SerializedName("windchill_c") val windchillC: Float,
    @SerializedName("windchill_f") val windchillF: Float,
    @SerializedName("heatindex_c") val heatIndexC: Float,
    @SerializedName("heatindex_f") val heatIndexF: Float,
    @SerializedName("dewpoint_c") val dewpointC: Float,
    @SerializedName("dewpoint_f") val dewpointF: Float,
    val condition: Condition,
    @SerializedName("wind_mph") val windMph: Float,
    @SerializedName("wind_kph") val windKph: Float,
    @SerializedName("wind_degree") val windDegree: Int,
    @SerializedName("wind_dir") val windDir: String,
    @SerializedName("pressure_mb") val pressureMb: Float,
    @SerializedName("pressure_in") val pressureIn: Float,
    @SerializedName("precip_mm") val precipMm: Float,
    @SerializedName("precip_in") val precipIn: Float,
    val humidity: Int,
    val cloud: Int,
    @SerializedName("is_day") val isDay: Int,
    @SerializedName("will_it_rain") val willItRain: Int,
    @SerializedName("chance_of_rain") val chanceOfRain: Int,
    @SerializedName("will_it_snow") val willItSnow: Int,
    @SerializedName("chance_of_snow") val chanceOfSnow: Int,
    val uv: Float,
    @SerializedName("gust_mph") val gustMph: Float,
    @SerializedName("gust_kph") val gustKph: Float,
    @SerializedName("short_rad") val shortRad: Float,
    @SerializedName("diff_rad") val diffRad: Float,
    val dni: Float,
    val gti: Float,
    @SerializedName("snow_cm") val snowCm: Float, //This is the only actual difference between Hour and Current besides the first two time fields, for some reason


)