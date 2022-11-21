package com.example.lessonandroid.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import com.example.lessonandroid.R
import com.example.lessonandroid.Sorting
import com.example.lessonandroid.databinding.FragmentBottomSortingBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SortingBottomSheet : BottomSheetDialogFragment() {

    private var _viewBinding: FragmentBottomSortingBinding? = null
    private val viewBinding get() = _viewBinding!!
    private var isId: Boolean = true
    private var currentSort: Sorting = Sorting.Id

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = FragmentBottomSortingBinding.inflate(inflater)
        initButtonsColor()
        initListeners()
        return viewBinding.root
    }

    private fun initButtonsColor() {
        isId = arguments?.getBoolean(ID_SORT) ?: true
        currentSort = (arguments?.getSerializable(SORT_KEY) ?: Sorting.Id) as Sorting

        when(currentSort){
            Sorting.Id, Sorting.IdDesc -> activeIdSort()
            Sorting.Name,Sorting.NameDesc -> activeAlphabetSort()
        }

        if (isId) {
            activeIdSort()
        } else {
            activeAlphabetSort()
        }

    }
    private fun initListeners() {
        with(viewBinding) {
            idSortBtn.setOnClickListener {
                activeIdSort()
                parentFragmentManager.setFragmentResult(SORT_KEY, bundleOf(ID_SORT to true))
                dismiss()
            }

            alphabetSortBtn.setOnClickListener {
                activeAlphabetSort()
                parentFragmentManager.setFragmentResult(SORT_KEY, bundleOf(ID_SORT to false))
                dismiss()

            }
        }
    }

    private fun activeIdSort() {
        with(viewBinding) {

            idSortBtn.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.light_green
                )
            )
            alphabetSortBtn.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.light_red
                )
            )
        }
    }

    private fun activeAlphabetSort() {
        with(viewBinding) {
            alphabetSortBtn.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.light_green
                )
            )
            idSortBtn.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.light_red
                )
            )
        }
    }

    override fun onDestroy() {
        _viewBinding = null
        super.onDestroy()
        Log.d("Check", "DESTROYED")
    }

    companion object {
        fun getInstance(bundle: Bundle) = SortingBottomSheet().apply { arguments = bundle }

        const val SORTING_BOTTOM_SHEET_TAG = "SORTING_BOTTOM_SHEET_TAG"
        const val SORT_KEY = "SORT_KEY"
        const val ID_SORT = "ID_SORT"
    }
}