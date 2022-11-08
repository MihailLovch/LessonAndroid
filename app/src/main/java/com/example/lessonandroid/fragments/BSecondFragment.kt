package com.example.lessonandroid.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lessonandroid.R
import com.example.lessonandroid.databinding.FragmentBSecondBinding


class BSecondFragment : Fragment(R.layout.fragment_b_second) {

    private val binding: FragmentBSecondBinding by viewBinding(FragmentBSecondBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editText.setText(arguments?.getString(BFragment.TEXT_KEY))
    }


}