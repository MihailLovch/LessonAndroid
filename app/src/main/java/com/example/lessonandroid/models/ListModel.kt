package com.example.lessonandroid.models

import com.example.lessonandroid.entities.Category
import com.example.lessonandroid.entities.ListenedAlbum

sealed class ListModel(var id: Int) {
    object FavoriteTracks : ListModel(0) {
        val pictures = listOf(
            "https://avatars.yandex.net/get-music-content/163479/7072a3e2.a.4924438-2/200x200",
            "https://avatars.yandex.net/get-music-content/139444/54e44017.a.4924440-2/200x200",
            "https://avatars.yandex.net/get-music-content/103235/20799d24.a.4416463-1/200x200"
        )
    }

    class CategoryModel(id: Int,val category: Category) : ListModel(id)
    class ListenedAlbumModel(id: Int,val listenedAlbum: ListenedAlbum) : ListModel(id)
}
