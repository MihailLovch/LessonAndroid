package com.example.lessonandroid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lessonandroid.R
import com.example.lessonandroid.databinding.FragmentCBinding


class CFragment : Fragment(R.layout.fragment_c) {

    private val binding: FragmentCBinding by viewBinding(FragmentCBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fromSheet =
            arguments?.getBoolean(MyBottomSheetDialogFragment.FROM_SHEET_NAVIGATION) ?: false

        if (fromSheet) {
            arguments?.putBoolean(MyBottomSheetDialogFragment.FROM_SHEET_NAVIGATION, false)
            findNavController().navigate(
                R.id.action_CFragment_to_CFirstFragment,
                bundleOf(MyBottomSheetDialogFragment.FROM_SHEET_NAVIGATION to true),
            )

        }
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_CFragment_to_CFirstFragment)
        }
    }


}