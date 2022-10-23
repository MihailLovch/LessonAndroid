package com.example.lessonandroid.entities

import com.example.lessonandroid.R

enum class ViewTypes(val viewType: Int) {
    FAVORITE(R.layout.item_favorite_music),
    CATEGORY(R.layout.item_categories),
    PLAYLIST(R.layout.item_playlist)
}