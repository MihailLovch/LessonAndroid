package com.example.lessonandroid.fragments

import android.os.Bundle
import android.view.View
import android.widget.Adapter
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.lessonandroid.R
import com.example.lessonandroid.adapters.CollectionRVAdapter
import com.example.lessonandroid.databinding.FragmentCollectionRecyclerViewBinding
import com.example.lessonandroid.entities.Category
import com.example.lessonandroid.entities.ListenedAlbum
import com.example.lessonandroid.models.ListModel

class CollectionRVFragment : Fragment(R.layout.fragment_collection_recycler_view) {

    private val viewBinding: FragmentCollectionRecyclerViewBinding by viewBinding(
        FragmentCollectionRecyclerViewBinding::bind
    )
    private var adapter: CollectionRVAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initAdapter()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initAdapter() {
        adapter = CollectionRVAdapter(
            items = listOf<ListModel>(
                ListModel.FavoriteTracks,
                ListModel.CategoryModel(Category(R.drawable.ic_playlists,"Playlists")),
                ListModel.CategoryModel(Category(R.drawable.ic_playlists,"Tracks")),
                ListModel.CategoryModel(Category(R.drawable.ic_playlists,"Albums")),
                ListModel.CategoryModel(Category(R.drawable.ic_playlists,"Artists")),
                ListModel.CategoryModel(Category(R.drawable.ic_playlists,"Podcasts & books")),
                ListModel.CategoryModel(Category(R.drawable.ic_playlists,"Downloaded tracks")),
                ListModel.CategoryModel(Category(R.drawable.ic_playlists,"Tracks from device")),
                ListModel.CategoryModel(Category(R.drawable.ic_playlists,"For kids")),
                ListModel.Title,
                ListModel.ListenedAlbumModel(ListenedAlbum("Tragic City","https://upload.wikimedia.org/wikipedia/ru/f/fb/Tragic_City.jpg")),
                ListModel.ListenedAlbumModel(ListenedAlbum("Die Lit","https://upload.wikimedia.org/wikipedia/ru/thumb/c/c6/Die_Lit_by_Playboi_Carti.jpg/274px-Die_Lit_by_Playboi_Carti.jpg")),
                ListModel.ListenedAlbumModel(ListenedAlbum("Portamento","https://upload.wikimedia.org/wikipedia/en/f/f5/Portamento.jpg")),
                ListModel.ListenedAlbumModel(ListenedAlbum("Rapp2","https://images.genius.com/b97d65ea4e0076b33617031fe8d282d5.1000x1000x1.jpg")),
                ListModel.ListenedAlbumModel(ListenedAlbum("Rapp2","https://images.genius.com/b97d65ea4e0076b33617031fe8d282d5.1000x1000x1.jpg")),
                ListModel.ListenedAlbumModel(ListenedAlbum("Rapp2","https://images.genius.com/b97d65ea4e0076b33617031fe8d282d5.1000x1000x1.jpg")),
                ListModel.ListenedAlbumModel(ListenedAlbum("Rapp2","https://images.genius.com/b97d65ea4e0076b33617031fe8d282d5.1000x1000x1.jpg")),
            ),
            glide = Glide.with(this)
        )
        viewBinding.collectionRv.adapter = adapter

    }

    companion object {
        fun getInstance() = CollectionRVFragment()

        const val COLLECTION_FRAGMENT_TAG = "COLLECTION_FRAGMENT_TAG"
    }
}