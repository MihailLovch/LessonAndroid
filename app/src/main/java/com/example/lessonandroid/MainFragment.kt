package com.example.lessonandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lessonandroid.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private var counter = 0
    private var colorCounter = 0
    private val fragmentContainerId: Int = R.id.main_fragments_container

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        initClickListeners()
        return binding.root
    }

    private fun initClickListeners() {
        with(binding) {

            navBtn.setOnClickListener {

                parentFragmentManager.beginTransaction()
                    .replace(
                        fragmentContainerId,
                        SecondFragment.getInstance().apply {
                            arguments = Bundle().apply {
                                putInt(COUNTER_KEY, counter)
                                putInt(COLOR_KEY, colorCounter)
                            }
                        },
                        SecondFragment.SECOND_PAGE_FRAGMENT_TAG
                    )
                    .addToBackStack(null)
                    .commit()
            }

            changeColorBtn.setOnClickListener {
                colorCounter++
                colorCounter %= COLOR_AMOUNT
            }
            incrementBtn.setOnClickListener {
                counter++
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(COLOR_KEY,colorCounter)
        outState.putInt(COUNTER_KEY,counter)
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt(COUNTER_KEY)
            colorCounter = savedInstanceState.getInt(COLOR_KEY)
        }
        super.onViewStateRestored(savedInstanceState)
    }
    companion object {
        const val MAIN_PAGE_FRAGMENT_TAG = "MAIN_PAGE_FRAGMENT_TAG"
        const val COLOR_AMOUNT = 5
        const val COUNTER_KEY = "COUNTER_KEY"
        const val COLOR_KEY = "COLOR_KEY"

        fun getInstance(bundle: Bundle?) = MainFragment().apply { arguments = bundle }
    }
}