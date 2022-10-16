package com.example.lessonandroid.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.RequestManager
import com.example.lessonandroid.R
import com.example.lessonandroid.databinding.ItemTextViewSampleBinding
import com.example.lessonandroid.entities.City
import com.example.lessonandroid.fragments.RecyclerViewFragment
import com.example.lessonandroid.models.CityModel

class RecyclerViewAdapter(
    val glide: RequestManager,
    val items: List<CityModel>,
    val onItemClick: ((City) -> Unit),
) : RecyclerView.Adapter<RecyclerViewAdapter.TextViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        return TextViewHolder(
            viewBinding = ItemTextViewSampleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        holder.bindItem(items[position])

    }

    override fun getItemCount(): Int = items.size


    inner class TextViewHolder(
        private val viewBinding: ItemTextViewSampleBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bindItem(cityModel: CityModel) {
            with(viewBinding) {
                cityNameTv.text = cityModel.city.name
                if (cityModel.isVisited){
                    root.background = ContextCompat.getDrawable(itemView.context,R.color.purple_200)
                }
                root.setOnClickListener {
                    onItemClick(cityModel.city)
                    cityModel.isVisited = true
                }

                glide
                    .load(cityModel.city.url)
                    .placeholder(RecyclerViewFragment.getCircularProgressDrawable(itemView.context))
                    .into(elementIv)
            }
        }
    }


}