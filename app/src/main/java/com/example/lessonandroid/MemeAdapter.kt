package com.example.lessonandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.lessonandroid.databinding.ViewPagerItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MemeAdapter(
    private val items: List<ImageModel>,
    private val glide: RequestManager?,
    private val coroutineScope: CoroutineScope,
) : RecyclerView.Adapter<MemeAdapter.MemeViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemeAdapter.MemeViewHolder =
        MemeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_pager_item, parent, false))

    override fun onBindViewHolder(holder: MemeAdapter.MemeViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int = items.size

    inner class MemeViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {
        private val viewBinding = ViewPagerItemBinding.bind(view)

        fun onBind(position: Int) {
            coroutineScope.launch {
                if (!items[position].loaded) {
                    viewBinding.loadPb.visibility = View.VISIBLE
                    delay(3000)
                    glide?.load(items[position].URL)?.preload()
                    items[position].loaded = true
                    viewBinding.loadPb.visibility = View.INVISIBLE
                }
                glide?.load(items[position].URL)?.into(viewBinding.imageIv)
            }
        }
    }
}