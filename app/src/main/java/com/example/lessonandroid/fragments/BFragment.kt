package com.example.lessonandroid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lessonandroid.R
import com.example.lessonandroid.databinding.FragmentBBinding


class BFragment : Fragment(R.layout.fragment_b) {

    private val binding: FragmentBBinding by viewBinding(FragmentBBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fromSheet =
            arguments?.getBoolean(MyBottomSheetDialogFragment.FROM_SHEET_NAVIGATION) ?: false

        if (fromSheet) {
            arguments?.putBoolean(MyBottomSheetDialogFragment.FROM_SHEET_NAVIGATION, false)
            findNavController().navigate(
                R.id.action_BFragment_to_BSecondFragment,
                bundleOf(MyBottomSheetDialogFragment.FROM_SHEET_NAVIGATION to true),
            )

        }

        binding.button.setOnClickListener {
            val text = binding.editText.text.toString()
            findNavController().navigate(
                R.id.action_BFragment_to_BSecondFragment,
                bundleOf(TEXT_KEY to text)
            )
        }
    }

    companion object {
        const val TEXT_KEY = "edit_text"
    }
}