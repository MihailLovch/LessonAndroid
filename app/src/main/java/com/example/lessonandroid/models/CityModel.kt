package com.example.lessonandroid.models

import com.example.lessonandroid.entities.City

data class CityModel(
    var city: City,
    var isVisited: Boolean = false,
)
