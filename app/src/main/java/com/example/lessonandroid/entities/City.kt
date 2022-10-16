package com.example.lessonandroid.entities

data class City(
    val id: Int,
    val name: String,
    val country: String,
    val url: String = "",
    val population: Int,
){
    companion object{
        const val ID = "ID"
    }
}

