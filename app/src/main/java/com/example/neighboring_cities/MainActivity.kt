package com.example.neighboring_cities


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.gson.Gson
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var cities: Array<City>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cities_stream = resources.openRawResource(R.raw.cities)
        val gson = Gson()
        val cities_data = gson.fromJson(InputStreamReader(cities_stream), Cities::class.java)
        Log.d("mytag", "Loaded movies ${cities_data.cities.size}")
        cities = cities_data.cities
        val arrNameCity = Array(cities.size) {""}
        for (i in 0 until cities.size - 1) {
            Log.d("mytag", i.toString())
            arrNameCity[i] = cities[i].name
        }

        val spinner = findViewById<Spinner>(R.id.spinner)
        val adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, arrNameCity)
        spinner.adapter = adapter
    }

    fun onNextActivity(view: View) {
        val distance_et: EditText = findViewById(R.id.distance)
        val distance: Int = Integer.parseInt(distance_et.text.toString())

        val spinner = findViewById<Spinner>(R.id.spinner)
        val selectCity = spinner.selectedItemId
        Log.d("float", selectCity.toString())

        val intent = Intent(applicationContext, CityActivity::class.java)
        intent.putExtra("distance", distance)
        intent.putExtra("city", selectCity.toInt())
        startActivity(intent)
    }
}