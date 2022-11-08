package com.example.lessonandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.lessonandroid.databinding.ItemRecyclerBinding
import com.example.lessonandroid.fragments.MyBottomSheetDialogFragment
import com.example.lessonandroid.models.FragmentModel

class FragmentsAdapter(
    private var items: List<FragmentModel>,
    private val fragment: Fragment
) : RecyclerView.Adapter<FragmentsAdapter.FragmentsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FragmentsAdapter.FragmentsViewHolder =
        FragmentsViewHolder(ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context)), fragment)

    override fun onBindViewHolder(holder: FragmentsAdapter.FragmentsViewHolder, position: Int) {
        holder.onBind(position)
        val parent: ViewGroup
    }

    override fun getItemCount(): Int = items.size

    inner class FragmentsViewHolder(
        private val binding: ItemRecyclerBinding,
        private val fragment: Fragment
    ) : RecyclerView.ViewHolder(binding.root) {
        public fun onBind(position: Int) {
            with(binding){
                val item = items[position]
                nameTv.text = items[position].name
                root.setOnClickListener {
                    fragment.findNavController().navigate(items[position].actionId,
                    bundleOf(MyBottomSheetDialogFragment.FROM_SHEET_NAVIGATION to true))
                }
            }
        }
    }
}