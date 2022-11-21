package com.example.lessonandroid.models

object ListModelRepository {
    private var i: Int = 1
    var items = listOf(
        ListModel(i++, "Wildberries"),
        ListModel(i++, "Ozon"),
        ListModel(i++, "Citylink"),
        ListModel(i++, "DNS"),
        ListModel(i++, "Mvideo"),
        ListModel(i++, "Eldorado"),
        ListModel(i++, "Lamoda"),
        ListModel(i++, "Apteka"),
        ListModel(i++, "Aliexpress"),
        ListModel(i++, "Ikea"),
        ListModel(i++, "Leroymerlin"),
        ListModel(i++, "Svyaznoy"),
        ListModel(i++, "Sportmaster"),
        ListModel(i++, "MTS"),
        ListModel(i++, "Sbermarket"),
    )

    fun sortById(){
        items = items.sortedBy { it.id }
    }

    fun sortByName(){
        items = items.sortedBy { it.name }
    }

    fun sortByIdDesc(){
        items = items.sortedByDescending { it.id }
    }

    fun sortByNameDesc(){
        items = items.sortedByDescending { it.name }
    }

}
