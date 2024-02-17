package com.example.train.UI

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.train.R
import com.example.train.data.WeatherData
import com.example.train.viewmodel.WeatherViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var locationText: EditText
    private lateinit var cityText: TextView
    private lateinit var tempText: TextView
    private lateinit var descText: TextView
    private lateinit var image: ImageView
    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        image = findViewById(R.id.imageView)
        cityText = findViewById(R.id.city)
        tempText = findViewById(R.id.temp)
        descText = findViewById(R.id.desc)
        locationText = findViewById(R.id.location)

        button.setOnClickListener {

            if(locationText.text.toString().trim().equals("")) {
                Toast.makeText(this, "Type your location!", Toast.LENGTH_LONG).show()
            } else {
                val city = locationText.text.toString()
                getWeatherData(city)
            }
        }
        val observeWeather = Observer<WeatherData> {newValue ->
            val tempInCelsius = newValue.main.temp - 273.15
            cityText.text = newValue.name
            val iconCode = newValue.weather.firstOrNull()?.icon
            if (iconCode != null) {
                Glide.with(this@MainActivity)
                    .load("http://openweathermap.org/img/wn/10n.png")
                    .override(200, 200)
                    .into(image)
            }
            tempText.text = String.format("%.2f°C", tempInCelsius)
            descText.text = newValue.weather.firstOrNull()?.description ?: "Описание недоступно"
        }

        weatherViewModel.currentWeather.observe(this, observeWeather)

        savedInstanceState?.let { bundle ->
            locationText.setText(bundle.getString("locTextContent", ""))
            cityText.text = bundle.getString("cityTextContent", "")
            tempText.text = bundle.getString("tempTextContent", "")
            descText.text = bundle.getString("descTextContent", "")
        }
    }

    private fun getWeatherData(cityName: String) {
        weatherViewModel.getWeather(cityName)
    }


    //сохранение состояния
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("locTextContent", locationText.text.toString())
        outState.putString("cityTextContent", cityText.text.toString())
        outState.putString("tempTextContent", tempText.text.toString())
        outState.putString("descTextContent", descText.text.toString())
    }

    //восстановление состояния
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        locationText.setText(savedInstanceState.getString("locTextContent"))
        cityText.text = savedInstanceState.getString("cityTextContent")
        tempText.text = savedInstanceState.getString("tempTextContent")
        descText.text = savedInstanceState.getString("descTextContent")
    }
}