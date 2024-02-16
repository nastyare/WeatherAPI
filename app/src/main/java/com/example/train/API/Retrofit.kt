package com.example.train.API

import com.example.train.data.WeatherData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("data/2.5/weather")
    suspend fun getWeather(@Query("q") cityName: String, @Query("appid") apiKey: String, @Query("lang") lang: String): WeatherData
}
object RetrofitService {
    private val baseUrl = "https://api.openweathermap.org/"

    fun createService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
