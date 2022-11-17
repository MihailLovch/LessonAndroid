package com.example.lessonandroid

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lessonandroid.databinding.FragmentListBinding
import com.example.lessonandroid.models.ListModel
import com.example.lessonandroid.models.ListModelRepository

class ListFragment : Fragment(R.layout.fragment_list) {

    private val viewBinding: FragmentListBinding by viewBinding(FragmentListBinding::bind)
    private var adapter: SortingListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        adapter = SortingListAdapter(
            ListModelRepository.items
        )
        viewBinding.shopsRv.adapter = adapter
    }

    companion object{
        fun getInstance() = ListFragment()

        const val LIST_FRAGMENT_TAG = "LIST_FRAGMENT_TAG"
    }
}