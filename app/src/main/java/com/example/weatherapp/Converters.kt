package com.example.weatherapp


// Assumes format: YYYY-MM-DD, like strings returned from weatherApi
fun ConvertDate(date: String): List<Int> {
    val year = date.substring(0,4).toInt()
    val month = date.substring(5,7).toInt() - 1 //Month is zero-indexed by Calendar
    val day = date.substring(8).toInt()

    return listOf<Int>(year,month,day)
}

fun UpresIcon(link: String): String {
    //TODO: Figure out a version of this line that's less dumb
    return "${link.substring(0, link.indexOf('6'))}128x128${link.substring(link.indexOf('6')+5)}"
}