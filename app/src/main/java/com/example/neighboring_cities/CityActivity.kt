package com.example.neighboring_cities

import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.gson.Gson
import java.io.InputStreamReader

class CityActivity : AppCompatActivity() {
    var distance: Int = 0
    var cityId: Int = 0
    lateinit var neighborsCities: Cities
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)

        val citiesStream = resources.openRawResource(R.raw.cities)
        val gson = Gson()
        neighborsCities = gson.fromJson(InputStreamReader(citiesStream), Cities::class.java)

        distance = intent.getIntExtra("distance", 0)
        cityId = intent.getIntExtra("city", 0)

        val neighborsInRadius = mutableListOf<String>()
        val result = FloatArray(1)

        for (i in neighborsCities.cities.indices) {
            if (i == cityId) continue

            Location.distanceBetween(
                neighborsCities.cities[i].coord.lat, neighborsCities.cities[i].coord.lon,
                neighborsCities.cities[cityId].coord.lat,
                neighborsCities.cities[cityId].coord.lon, result)

            if (result.first() <= distance) {
                neighborsInRadius.add(neighborsCities.cities[i].name)
            }
        }

        val listView = findViewById<ListView>(R.id.list)
        val adapter = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, neighborsInRadius)
        listView.adapter = adapter
    }

}