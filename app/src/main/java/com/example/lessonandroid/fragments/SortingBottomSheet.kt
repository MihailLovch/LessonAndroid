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
        currentSort = (arguments?.getSerializable(SORT_KEY) ?: Sorting.Id) as Sorting

        when (currentSort) {
            Sorting.Id, Sorting.IdDesc -> activeIdSort()
            Sorting.Name, Sorting.NameDesc -> activeAlphabetSort()
        }
    }

    private fun initListeners() {
        with(viewBinding) {
            idSortBtn.setOnClickListener {
                val newSorting = when (currentSort) {
                    Sorting.Id -> Sorting.IdDesc
                    Sorting.IdDesc -> Sorting.Id
                    else -> Sorting.Id
                    //можно упростить, но так легче понимать логику
                }
                parentFragmentManager.setFragmentResult(SORT_KEY, bundleOf(SORT_KEY to newSorting))
                dismiss()

            }

            alphabetSortBtn.setOnClickListener {
                val newSorting = when (currentSort) {
                    Sorting.Name -> Sorting.NameDesc
                    Sorting.NameDesc -> Sorting.Name
                    else -> Sorting.Name
                }
                parentFragmentManager.setFragmentResult(SORT_KEY, bundleOf(SORT_KEY to newSorting))
                dismiss()

            }
        }
    }

    private fun activeIdSort() {
        with(viewBinding) {
            when (currentSort) {
                Sorting.Id -> {
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
                Sorting.IdDesc -> {
                    idSortBtn.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.dark_green
                        )
                    )
                }
                else -> throw Exception("Not provided")
            }
        }
    }

    private fun activeAlphabetSort() {
        with(viewBinding) {

            when (currentSort) {
                Sorting.Name -> {
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
                Sorting.NameDesc -> {
                    alphabetSortBtn.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.dark_green
                        )
                    )
                }
                else -> throw Exception("Not supported")
            }
        }
    }

    override fun onDestroy() {
        _viewBinding = null
        super.onDestroy()
    }

    companion object {
        fun getInstance(bundle: Bundle) = SortingBottomSheet().apply { arguments = bundle }

        const val SORTING_BOTTOM_SHEET_TAG = "SORTING_BOTTOM_SHEET_TAG"
        const val SORT_KEY = "SORT_KEY"
        const val ID_SORT = "ID_SORT"
    }
}