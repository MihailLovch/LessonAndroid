package com.example.lessonandroid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lessonandroid.R
import com.example.lessonandroid.databinding.FragmentASecondBinding


class CSecondFragment : Fragment(R.layout.fragment_c_second) {

    private val binding: FragmentASecondBinding by viewBinding(FragmentASecondBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        view.findViewById<BottomNavigationView>(R.id.botNavView).visibility = View.VISIBLE
    }


}