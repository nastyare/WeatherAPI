package com.example.train

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.train.data.WeatherData
import com.example.train.viewmodel.WeatherViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var locationText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //переход на новую активность
        button = findViewById(R.id.button)
        locationText = findViewById(R.id.location)

        button.setOnClickListener {
            if(locationText.text.toString().trim().equals("")) {
                Toast.makeText(this, "Type your location!", Toast.LENGTH_LONG).show()
            } else {
                var city = locationText.text.toString()
                val intent = Intent(this@MainActivity, NewActivity::class.java)
                intent.putExtra("loc", city)
                startActivity(intent)
            }
        }
    }
}