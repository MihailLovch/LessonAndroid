package com.example.lessonandroid.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.lessonandroid.R
import com.example.lessonandroid.adapters.RecyclerViewAdapter
import com.example.lessonandroid.databinding.FragmentRecyclerViewBinding
import com.example.lessonandroid.repositories.CityRepository

class RecyclerViewFragment : Fragment(R.layout.fragment_recycler_view) {

    private val viewBinding: FragmentRecyclerViewBinding by viewBinding(FragmentRecyclerViewBinding::bind)
    private var rvAdapter: RecyclerViewAdapter? = null
    private val fragmentContainerId = R.id.main_fragments_container

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        rvAdapter = RecyclerViewAdapter(
            items = CityRepository.cities,
            glide = Glide.with(this)
        ) {
            parentFragmentManager.beginTransaction()
                .replace(
                    fragmentContainerId,
                    CityInfoFragment.getInstance(it.id),
                    CityInfoFragment.CITY_INFO_FRAGMENT_TAG
                ).addToBackStack(null)
                .commit()
        }
        viewBinding.itemsRv.adapter = rvAdapter
    }

    companion object {
        const val RV_FRAGMENT_TAG = "RECYCLER_VIEW_FRAGMENT_TAG"

        fun getInstance() = RecyclerViewFragment()
        fun getCircularProgressDrawable(context: Context): CircularProgressDrawable {
            val circularProgressDrawable = CircularProgressDrawable(context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()
            return circularProgressDrawable
        }
    }
}