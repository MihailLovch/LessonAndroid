package com.example.lessonandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.lessonandroid.databinding.FragmentSecondBinding

class SecondFragment : Fragment(R.layout.fragment_second) {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private var counter: Int? = null
    private var colorCount: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater,container,false)
        counter = arguments?.getInt(MainFragment.COUNTER_KEY)
        colorCount = arguments?.getInt(MainFragment.COLOR_KEY)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeViews()

    }

    private fun changeViews() {
        changeTextView(counter)
        changeImageView(colorCount)
    }
    private fun changeTextView(counter : Int?){
        if (counter != null && counter != 0) {
            with(binding) {
                counterTv.text = getString(R.string.counter_value) + counter.toString()
                counterTv.visibility =TextView.VISIBLE
            }
        }
    }
    private fun changeImageView(colorCount:Int?){
        when (colorCount){
            0 -> binding.colorIv.setBackgroundResource(R.color.black)
            1 -> binding.colorIv.setBackgroundResource(R.color.teal_700)
            2 -> binding.colorIv.setBackgroundResource(R.color.yellow_200)
            3 -> binding.colorIv.setBackgroundResource(R.color.red_200)
        }
    }

    companion object {
        const val SECOND_PAGE_FRAGMENT_TAG = "SECOND_PAGE_FRAGMENT_TAG"

        fun getInstance() = SecondFragment()
    }
}