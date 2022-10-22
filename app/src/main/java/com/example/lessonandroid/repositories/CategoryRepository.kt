package com.example.lessonandroid.repositories

import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.ResourcesCompat
import com.example.lessonandroid.R
import com.example.lessonandroid.entities.Category

object CategoryRepository{
    val categories = listOf(
        Category(R.drawable.ic_playlists,"Playlists"),
        Category(R.drawable.ic_playlists,"Tracks"),
        Category(R.drawable.ic_playlists,"Albums"),
        Category(R.drawable.ic_playlists,"Artists"),
        Category(R.drawable.ic_playlists,"Podcasts & books"),
        Category(R.drawable.ic_playlists,"Downloaded tracks"),
        Category(R.drawable.ic_playlists,"Tracks from device"),
        Category(R.drawable.ic_playlists,"For kids"),
    )

}