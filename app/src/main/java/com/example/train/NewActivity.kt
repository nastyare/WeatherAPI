package com.example.train

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.train.viewmodel.WeatherViewModel

class NewActivity : AppCompatActivity() {

    private lateinit var infoText: TextView
    private lateinit var text: EditText
    private lateinit var returnButton: Button
    private val weatherViewModel: WeatherViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)


        returnButton = findViewById(R.id.returnButton)
        var getCity: String = intent.getStringExtra("loc").toString()

        infoText = findViewById(R.id.result)
        text = findViewById(R.id.resultLoc)
        text.setText(getCity)
        infoText.text = getCity


        var cityName = getCity
        getWeatherData(cityName)

        weatherViewModel.currentWeather.observe(this, { weatherResponse ->
            TODO()
        })

        //кнопка возврата
        returnButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun getWeatherData(cityName: String) {
        weatherViewModel.getWeather(cityName)
    }
}