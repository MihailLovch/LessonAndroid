package com.example.lessonandroid.repositories

import com.example.lessonandroid.R
import com.example.lessonandroid.entities.Category
import com.example.lessonandroid.entities.ListenedAlbum
import com.example.lessonandroid.models.ListModel

object ListModelRepository {
    var i = 1;
    val list = mutableListOf<ListModel>(
        ListModel.FavoriteTracks,
        ListModel.CategoryModel(i++,Category(R.drawable.ic_playlists, "Playlists")),
        ListModel.CategoryModel(i++,Category(R.drawable.ic_baseline_queue_music_24, "Tracks")),
        ListModel.CategoryModel(i++,Category(R.drawable.ic_baseline_album_24, "Albums")),
        ListModel.CategoryModel(i++,Category(R.drawable.ic_baseline_recent_actors_24, "Artists")),
        ListModel.CategoryModel(i++,Category(R.drawable.ic_baseline_books, "Podcasts & books")),
        ListModel.CategoryModel(i++,Category(R.drawable.ic_outline_file_download_24, "Downloaded tracks")),
        ListModel.CategoryModel(i++,Category(R.drawable.ic_baseline_storage_24, "Tracks from device")),
        ListModel.CategoryModel(i++,Category(R.drawable.ic_baseline_child_care_24, "For kids")),
        ListModel.ListenedAlbumModel(
            i++,
            ListenedAlbum(
                "Tragic City",
                "https://avatars.yandex.net/get-music-content/4399834/0415f8b8.a.17439837-1/200x200",
            )
        ),
        ListModel.ListenedAlbumModel(
            i++,
            ListenedAlbum(
                "Die Lit",
                "https://avatars.yandex.net/get-music-content/98892/5ae23a9d.a.5368515-1/200x200"
            )
        ),
        ListModel.ListenedAlbumModel(
            i++,
            ListenedAlbum(
                "Portamento",
                "https://avatars.yandex.net/get-music-content/4399834/6c84eb80.a.17770346-1/200x200"
            )
        ),
        ListModel.ListenedAlbumModel(
            i++,
            ListenedAlbum(
                "Rapp2",
                "https://avatars.yandex.net/get-music-content/117546/736917ab.a.6117897-1/200x200"
            )
        ),
        ListModel.ListenedAlbumModel(
            i++,
            ListenedAlbum(
                "Toxicity",
                "https://avatars.yandex.net/get-music-content/142001/5f909cfc.a.624758-3/200x200"
            )
        ),
        ListModel.ListenedAlbumModel(
            i++,
            ListenedAlbum(
                "ОМОФОР",
                "https://avatars.yandex.net/get-music-content/4384958/69e591a1.a.15512820-1/200x200"
            )
        ),
    )
}