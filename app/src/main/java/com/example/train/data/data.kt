package com.example.train.data

data class WeatherData(
    val current: CurrentWeather
)
data class CurrentWeather (
    val temp: Double,
    val weather: WeatherInfo
)
data class WeatherInfo (
    val description: String
)
