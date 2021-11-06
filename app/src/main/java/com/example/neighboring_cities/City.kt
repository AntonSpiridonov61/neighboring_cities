package com.example.neighboring_cities

data class City(val id: Int,
                val name: String,
                val state: String,
                val country: String,
                val coord: HashMap<String, Double>) {

}