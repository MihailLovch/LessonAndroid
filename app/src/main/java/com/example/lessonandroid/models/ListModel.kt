package com.example.lessonandroid.models

import com.example.lessonandroid.entities.Category
import com.example.lessonandroid.entities.ListenedAlbum

sealed class ListModel {
    object FavoriteTracks : ListModel() {
        val pictures = listOf(
            "https://upload.wikimedia.org/wikipedia/ru/f/fb/Tragic_City.jpg",
            "https://images.genius.com/b97d65ea4e0076b33617031fe8d282d5.1000x1000x1.jpg",
            "https://images.genius.com/b97d65ea4e0076b33617031fe8d282d5.1000x1000x1.jpg"
        )
    }

    class CategoryModel(val category: Category) : ListModel()
    object Title : ListModel()
    class ListenedAlbumModel(val listenedAlbum: ListenedAlbum) : ListModel()
}

