package com.example.lessonandroid

import android.app.ActionBar
import android.os.Bundle
import android.text.BoringLayout
import android.util.DisplayMetrics
import android.view.Display
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lessonandroid.databinding.FragmentSecondBinding

class SecondFragment : Fragment(R.layout.fragment_second) {

    private val binding: FragmentSecondBinding by viewBinding(FragmentSecondBinding::bind)
    private lateinit var metrics: DisplayMetrics
    private var viewHeight: Int = 0
    private var viewWidth: Int = 0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeImageViews()
    }

    private fun changeImageViews() {
        initMetrics()
        changeSize()
        changeConstraints()
    }

    private fun changeSize() {
        with(binding) {
            arrayOf(leftBotIv, leftTopIv, rightBotIv, rightTopIv)
                .forEach {
                    it.layoutParams.height = viewHeight
                    it.layoutParams.width = viewWidth
                }
        }
    }

    private fun changeConstraints() {
        with(binding) {
            arrayOf(
                leftBotIv.layoutParams as ConstraintLayout.LayoutParams,
                leftTopIv.layoutParams as ConstraintLayout.LayoutParams,
                rightBotIv.layoutParams as ConstraintLayout.LayoutParams,
                rightTopIv.layoutParams as ConstraintLayout.LayoutParams
            )
                .forEach {
                    it.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                    it.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                    it.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                    it.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
                    if (it == leftBotIv.layoutParams as ConstraintLayout.LayoutParams) {
                        it.horizontalBias = 0F
                        it.verticalBias = 1F
                    }
                    if (it == leftTopIv.layoutParams as ConstraintLayout.LayoutParams) {
                        it.horizontalBias = 0F
                        it.verticalBias = 0F
                    }
                    if (it == rightBotIv.layoutParams as ConstraintLayout.LayoutParams) {
                        it.horizontalBias = 1F
                        it.verticalBias = 1F
                    }
                    if (it == rightTopIv.layoutParams as ConstraintLayout.LayoutParams) {
                        it.horizontalBias = 1F
                        it.verticalBias = 0F
                    }
                }
        }
    }

    private fun initMetrics() {
        metrics = requireContext().resources.displayMetrics
        viewHeight = metrics.heightPixels / 2
        viewWidth = metrics.widthPixels / 2
    }

    companion object {
        const val SECOND_PAGE_FRAGMENT_TAG = "SECOND_PAGE_FRAGMENT_TAG"

        fun getInstance() = SecondFragment()
    }
}