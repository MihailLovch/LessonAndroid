package com.example.lessonandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lessonandroid.databinding.ItemBinding
import com.example.lessonandroid.models.ListModel

class SortingListAdapter(
    private val items: List<ListModel>
) : ListAdapter<ListModel, SortingListAdapter.ItemViewHolder>(object :
    DiffUtil.ItemCallback<ListModel>() {
    override fun areItemsTheSame(
        oldItem: ListModel,
        newItem: ListModel
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: ListModel,
        newItem: ListModel
    ): Boolean = oldItem == newItem

}) {
    override fun getItemCount(): Int = items.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(
            ItemBinding.inflate(LayoutInflater.from(parent.context))
        )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.onBind(position)
    }


    inner class ItemViewHolder(
        private val binding: ItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) {
            with(binding) {
                nameTv.text = items[position].name
            }
        }
    }
}