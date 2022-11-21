package com.example.lessonandroid.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lessonandroid.databinding.ItemBinding
import com.example.lessonandroid.models.ListModel

class SortingListAdapter(
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

    override fun getItemCount(): Int = currentList.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(
            ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
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
                idTv.text = currentList[position].id.toString()
                nameTv.text = currentList[position].name
            }
        }
    }
}