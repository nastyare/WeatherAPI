package com.example.train.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.train.API.RetrofitService
import com.example.train.data.WeatherData
import kotlinx.coroutines.launch


class WeatherViewModel : ViewModel() {

    private val apiService = RetrofitService.createService()
    private val _currentWeather = MutableLiveData<WeatherData>()
    private val lang = "ru"

    val currentWeather: LiveData<WeatherData>
        get() = _currentWeather

    fun getWeather(cityName: String) {
        viewModelScope.launch {
            try {
                val response = apiService.getWeather(cityName, "e8e57c0253712af6201e66f3d7da762e", lang)
                _currentWeather.value = response
            } catch (e: Exception) {
                Log.d("WeatherViewModel", "Error in getWeather: ${e.message}")
                e.printStackTrace()
            }
        }
    }

}