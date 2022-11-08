package com.example.lessonandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lessonandroid.R
import com.example.lessonandroid.adapter.FragmentsAdapter
import com.example.lessonandroid.databinding.FragmentMyBottomBinding
import com.example.lessonandroid.models.FragmentModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class MyBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private var adapter: FragmentsAdapter? = null
    private var _binding: FragmentMyBottomBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyBottomBinding.inflate(inflater)
        initAdapter()

        return binding.root
    }
    private fun initAdapter() {
        adapter = FragmentsAdapter(
            listOf(
                FragmentModel(R.drawable.ic_java, "A3", R.id.action_myBottomSheetDialogFragment_to_nav_graph_a),
                FragmentModel(R.drawable.ic_java, "B2", R.id.action_myBottomSheetDialogFragment_to_nav_graph_b),
                FragmentModel(R.drawable.ic_java, "C2", R.id.action_myBottomSheetDialogFragment_to_nav_graph_c),
            ), this
        )
        binding.fragmentsRv.adapter = adapter
    }

    companion object {
        fun newInstance() = MyBottomSheetDialogFragment()
        const val FROM_SHEET_NAVIGATION = "FROM_SHEET_NAVIGATION"
    }
}