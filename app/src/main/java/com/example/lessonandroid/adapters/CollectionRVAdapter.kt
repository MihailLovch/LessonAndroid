package com.example.lessonandroid.adapters

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.bumptech.glide.RequestManager
import com.example.lessonandroid.R
import com.example.lessonandroid.databinding.ItemCategoriesBinding
import com.example.lessonandroid.databinding.ItemFavoriteMusicBinding
import com.example.lessonandroid.databinding.ItemPlaylistBinding
import com.example.lessonandroid.entities.Category
import com.example.lessonandroid.entities.ListenedAlbum
import com.example.lessonandroid.entities.ViewTypes
import com.example.lessonandroid.models.ListModel
import com.example.lessonandroid.repositories.ListModelRepository
import java.lang.IllegalStateException
import kotlin.random.Random

class CollectionRVAdapter(
    private var items: MutableList<ListModel>,
    public var glide: RequestManager,
) : RecyclerView.Adapter<CollectionRVAdapter.MegaViewHolder>() {

    override fun getItemViewType(position: Int): Int =
        when (items[position]) {
            is ListModel.FavoriteTracks -> ViewTypes.FAVORITE.viewType
            is ListModel.CategoryModel -> ViewTypes.CATEGORY.viewType
            is ListModel.ListenedAlbumModel -> ViewTypes.PLAYLIST.viewType
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CollectionRVAdapter.MegaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            ViewTypes.FAVORITE.viewType -> FavoriteViewHolder(view, glide)
            ViewTypes.PLAYLIST.viewType -> PlaylistViewHolder(view, glide) { addItem() }
            ViewTypes.CATEGORY.viewType -> CategoryViewHolder(view, glide)
            else -> throw IllegalStateException("Not implemented")
        }
    }

    override fun onBindViewHolder(holder: CollectionRVAdapter.MegaViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int = items.size


    private fun addItem() {
        val position = Random.nextInt(1, items.size)
        val newList = ArrayList(items)
        val index = ListModelRepository.i++

        val elemToInsert: ListModel = when (items[position]) {
            is ListModel.CategoryModel -> ListModel.CategoryModel(index, Category(index))
            is ListModel.ListenedAlbumModel -> ListModel.ListenedAlbumModel(
                index,
                ListenedAlbum(index)
            )
            is ListModel.FavoriteTracks -> throw IllegalStateException()
        }
        newList.add(position, elemToInsert)
        if (items[position - 1] is ListModel.CategoryModel && items[position] is ListModel.ListenedAlbumModel) {
            notifyItemChanged(position) // перерисовка заголовка у первого элемента
        }
        setNewItem(newList)
    }

    private fun setNewItem(newItems: MutableList<ListModel>) {
        val diffUtilCallback = object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = items.size

            override fun getNewListSize(): Int = newItems.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return items[oldItemPosition].id == newItems[newItemPosition].id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return items[oldItemPosition] == newItems[newItemPosition]
            }
        }
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        diffResult.dispatchUpdatesTo(this)
        items.clear()
        items.addAll(newItems)

    }

    abstract inner class MegaViewHolder(
        private var view: View
    ) : RecyclerView.ViewHolder(view) {
        abstract fun onBind(position: Int)
    }


    inner class FavoriteViewHolder(
        private var view: View,
        private var glide: RequestManager,
    ) : MegaViewHolder(view) {
        private val viewBinding: ItemFavoriteMusicBinding by viewBinding(ItemFavoriteMusicBinding::bind)
        private var i = 0
        override fun onBind(position: Int) {
            with(viewBinding) {
                val item = items[position] as ListModel.FavoriteTracks
                loadImage(item)
                root.setOnClickListener {
                    i++
                    loadImage(item)
                }
            }
        }
        private fun loadImage(item: ListModel.FavoriteTracks){
            glide
                .load(item.pictures[i % item.pictures.size])
                .into(viewBinding.favoriteIv)
        }
    }

    inner class CategoryViewHolder(
        private var view: View,
        private var glide: RequestManager
    ) : MegaViewHolder(view) {
        private val viewBinding: ItemCategoriesBinding by viewBinding(ItemCategoriesBinding::bind)
        override fun onBind(position: Int) {
            val item = items[position] as ListModel.CategoryModel
            loadText(item)
            loadImage(item)
        }
        private fun loadText(item: ListModel.CategoryModel){
            viewBinding.titleTv.text = item.category.name
        }
        private fun loadImage(item: ListModel.CategoryModel){
            glide
                .load(item.category.imageId)
                .into(viewBinding.favoriteIv)
        }
    }

    inner class PlaylistViewHolder(
        private var view: View,
        private var glide: RequestManager,
        private var onItemClick: () -> Unit
    ) : MegaViewHolder(view) {
        private val viewBinding: ItemPlaylistBinding by viewBinding(ItemPlaylistBinding::bind)
        override fun onBind(position: Int) {
            val item = items[position] as ListModel.ListenedAlbumModel
            with(viewBinding) {
                loadText(item)
                drawGroupTitle(position)
                root.setOnClickListener {
                    onItemClick()
                }
                loadImage(item)
            }
        }

        private fun loadImage(item: ListModel.ListenedAlbumModel) {
            glide
                .load(item.listenedAlbum.cover)
                .into(viewBinding.favoriteIv)
        }

        private fun loadText(item: ListModel.ListenedAlbumModel) {
            viewBinding.titleTv.text = item.listenedAlbum.name
        }

        private fun drawGroupTitle(position: Int) {
            with(viewBinding) {
                if (items[position - 1] is ListModel.CategoryModel) {
                    commonTitleTv.visibility = View.VISIBLE
                } else {
                    commonTitleTv.visibility = View.GONE
                }
            }
        }
    }
}
