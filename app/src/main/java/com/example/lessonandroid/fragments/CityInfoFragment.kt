package com.example.lessonandroid.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.lessonandroid.R
import com.example.lessonandroid.databinding.FragmentCityInfoBinding
import com.example.lessonandroid.entities.City
import com.example.lessonandroid.repositories.CityRepository

class CityInfoFragment : Fragment(R.layout.fragment_city_info) {

    private val viewBinding: FragmentCityInfoBinding by viewBinding(FragmentCityInfoBinding::bind)
    private var city: City? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initValues()
        changeViews(view)
    }

    private fun changeViews(view: View) {
        with(viewBinding) {
            cityNameTv.text = city?.name
            countryTv.text = getString(R.string.country,city?.country)
            populationTv.text = getString(R.string.population, city?.population)
            Glide.with(view)
                .load(city?.url)
                .placeholder(RecyclerViewFragment.getCircularProgressDrawable(view.context))
                .into(cityPhotoIv)
        }
    }

    private fun initValues() {
        city = CityRepository.cities[arguments?.getInt(City.ID) ?: 0].city
    }

    companion object {
        const val CITY_INFO_FRAGMENT_TAG = "CITY_INFO_FRAGMENT_TAG"
        fun getInstance(id: Int) =
            CityInfoFragment().apply { arguments = Bundle().apply { putInt(City.ID, id) } }
    }
}