package com.example.train.data

data class WeatherData(
    val weather: List<Weather>,
    val main: Main
)
data class Weather (
    val description: String
)
data class Main (
    val temp: Double
)
