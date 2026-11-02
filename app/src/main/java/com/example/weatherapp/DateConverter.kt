package com.example.weatherapp

class DateConverter {
    // Assumes format: YYYY-MM-DD, like strings returned from weatherApi
    public fun Convert(date: String): List<Int> {
        val year = date.substring(0,4).toInt()
        val month = date.substring(5,7).toInt() - 1 //Month is zero-indexed by Calendar
        val day = date.substring(8).toInt()

        return listOf<Int>(year,month,day)
    }
}