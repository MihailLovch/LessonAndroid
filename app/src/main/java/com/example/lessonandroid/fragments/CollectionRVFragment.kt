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
import com.example.lessonandroid.repositories.ListModelRepository

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
            items = ListModelRepository.list,
            glide = Glide.with(this)
        )
        viewBinding.collectionRv.adapter = adapter

    }

    companion object {
        fun getInstance() = CollectionRVFragment()

        const val COLLECTION_FRAGMENT_TAG = "COLLECTION_FRAGMENT_TAG"
    }
}