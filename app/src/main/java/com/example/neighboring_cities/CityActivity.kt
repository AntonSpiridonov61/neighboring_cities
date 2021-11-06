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
//    var id_city: Int = 0
    lateinit var cities: Array<City>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)

        val cities_stream = resources.openRawResource(R.raw.cities)
        val gson = Gson()
        cities = gson.fromJson(InputStreamReader(cities_stream), Cities::class.java).cities

        distance = intent.getIntExtra("distance", 0)
        val id_city = intent.getIntExtra("city", 0)
//        cities = intent.getByteArrayExtra("cities")
        val list_view = findViewById<ListView>(R.id.list)
        val result = FloatArray(cities.size)
        Log.d("float", id_city.toString())

        for (i in 0..(cities.size - 1)) {
//            if (i == id_city) continue
            Location.distanceBetween(
                cities[id_city].coord["lat"]!!,
                cities[id_city].coord["lon"]!!,
                cities[i].coord["lat"]!!,
                cities[i].coord["lon"]!!, result)
            Log.d("float", cities[i].toString())
            Log.d("float", result[i].toString())

        }

        val resf = Array (cities.size) {0f}
        for (i in 0..(cities.size - 1)) {
            resf[i] = result[i]
        }
        Log.d("float", resf.size.toString())

//        val cities = arrayOf(distance, city)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resf)
        list_view.adapter = adapter
    }
}