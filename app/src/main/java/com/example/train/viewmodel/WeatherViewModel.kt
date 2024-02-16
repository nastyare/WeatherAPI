package com.example.train.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.train.API.Common
import com.example.train.data.WeatherData
import kotlinx.coroutines.launch


class WeatherViewModel : ViewModel() {

    private var mService = Common.retrofitService
    var apiKey: String = "9e8d6c63d79e6bdc261f47d01ac4e7a2"

    val currentWeather: MutableLiveData<WeatherData> by lazy {
        MutableLiveData<WeatherData>()
    }

    fun getWeather(cityName: String) {
        viewModelScope.launch {
            try {
                val response = mService.getCurrentWeather(cityName, apiKey)
                currentWeather.value = response
            } catch (e: Exception) {
                Log.d("viewModel", "smth went wrong in getWeather fun")
            }
        }
    }




    private fun convertKelvinToCelsius(tempKelvin: Double): String {
        val tempCelsius = tempKelvin - 273.15
        return String.format("%.1f", tempCelsius) + "°C"
    }

    private fun convertDescriptionToRussian(description: String): String {
        // Реализуйте логику для перевода описания погоды на русский язык
        // Здесь просто возвращается переданное описание без изменений
        return description
    }

}