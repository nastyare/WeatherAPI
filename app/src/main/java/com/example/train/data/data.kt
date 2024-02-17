package com.example.train.data

data class WeatherData(
    val weather: List<Weather>,
    val name: String,
    val main: Main
)
data class Weather (
    val description: String,
    val icon: String
)
data class Main (
    val temp: Double
)
