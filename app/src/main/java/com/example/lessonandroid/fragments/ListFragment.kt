package com.example.lessonandroid.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lessonandroid.R
import com.example.lessonandroid.Sorting
import com.example.lessonandroid.adapters.SortingListAdapter
import com.example.lessonandroid.databinding.FragmentListBinding
import com.example.lessonandroid.models.ListModel
import com.example.lessonandroid.models.ListModelRepository

class ListFragment : Fragment(R.layout.fragment_list) {

    private val viewBinding: FragmentListBinding by viewBinding(FragmentListBinding::bind)
    private var adapter: SortingListAdapter? = null
    private var isIdSort: Boolean = true
    private var currentSorting: Sorting = Sorting.Id

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initListeners()
        subscribeToChanges()
    }

    private fun initListeners() {
        with(viewBinding) {
            showBottomBtn.setOnClickListener {
                SortingBottomSheet.getInstance(bundleOf(SortingBottomSheet.SORT_KEY to currentSorting))
                    .show(parentFragmentManager, SortingBottomSheet.SORTING_BOTTOM_SHEET_TAG)
            }
            navigateCameraBtn.setOnClickListener {
                parentFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.main_fragments_container,
                        QRScannerFragment.getInstance(),
                        QRScannerFragment.QR_SCANNER_TAG
                    ).commit()
            }
        }
    }

    private fun subscribeToChanges() {
        setFragmentResultListener(SortingBottomSheet.SORT_KEY) { _, bundle ->
            currentSorting =
                (bundle.getSerializable(SortingBottomSheet.SORT_KEY) ?: Sorting.Id) as Sorting

            when (currentSorting) {
                Sorting.Id -> ListModelRepository.sortById()
                Sorting.IdDesc -> ListModelRepository.sortByIdDesc()
                Sorting.Name -> ListModelRepository.sortByName()
                Sorting.NameDesc -> ListModelRepository.sortByNameDesc()
            }
            adapter?.submitList(ListModelRepository.items)
        }
    }

    private fun initAdapter() {
        adapter = SortingListAdapter()
        adapter?.submitList(ListModelRepository.items)
        viewBinding.shopsRv.adapter = adapter
    }

    companion object {
        fun getInstance() = ListFragment()

        const val LIST_FRAGMENT_TAG = "LIST_FRAGMENT_TAG"
    }
}