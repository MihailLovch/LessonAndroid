package com.example.lessonandroid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.postDelayed
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lessonandroid.R
import com.example.lessonandroid.databinding.FragmentABinding


class AFragment : Fragment(R.layout.fragment_a) {

    private val binding: FragmentABinding by viewBinding(FragmentABinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fromSheet =
            arguments?.getBoolean(MyBottomSheetDialogFragment.FROM_SHEET_NAVIGATION) ?: false

        if (fromSheet) {
            arguments?.putBoolean(MyBottomSheetDialogFragment.FROM_SHEET_NAVIGATION, false)
            findNavController().navigate(
                R.id.action_AFragment_to_AFirstFragment,
                bundleOf(MyBottomSheetDialogFragment.FROM_SHEET_NAVIGATION to true)
            )

        }
        binding.navBtn.setOnClickListener {
            findNavController().navigate(R.id.action_AFragment_to_AFirstFragment)
        }

    }


}