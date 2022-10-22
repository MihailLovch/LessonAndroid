package com.example.lessonandroid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.RequestManager
import com.example.lessonandroid.R
import com.example.lessonandroid.databinding.ItemCategoriesBinding
import com.example.lessonandroid.databinding.ItemFavoriteMusicBinding
import com.example.lessonandroid.databinding.ItemPlaylistBinding
import com.example.lessonandroid.databinding.ItemTitleBinding
import com.example.lessonandroid.models.ListModel

class CollectionRVAdapter(
    private var items: List<ListModel>,
    public var glide: RequestManager,
) : RecyclerView.Adapter<CollectionRVAdapter.MegaViewHolder>() {

    override fun getItemViewType(position: Int): Int =
        when (items[position]) {
            is ListModel.FavoriteTracks -> R.layout.item_favorite_music
            is ListModel.CategoryModel -> R.layout.item_categories
            is ListModel.Title -> R.layout.item_title
            is ListModel.ListenedAlbumModel -> R.layout.item_playlist
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CollectionRVAdapter.MegaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.item_favorite_music -> FavoriteViewHolder(view, glide)
            R.layout.item_playlist -> PlaylistViewHolder(view, glide)
            R.layout.item_categories -> CategoryViewHolder(view, glide)
            R.layout.item_title -> TitleViewHolder(view, glide)
            else -> MegaViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: CollectionRVAdapter.MegaViewHolder, position: Int) {
        holder.onBind(items[position])

    }

    override fun getItemCount(): Int = items.size


    open inner class MegaViewHolder(
        private var view: View
    ) : RecyclerView.ViewHolder(view) {
        open fun onBind(item: ListModel) {}
    }


    inner class FavoriteViewHolder(
        private var view: View,
        private var glide: RequestManager
    ) : MegaViewHolder(view) {
        private val viewBinding: ItemFavoriteMusicBinding by viewBinding(ItemFavoriteMusicBinding::bind)
        override fun onBind(item: ListModel) {
            with(viewBinding){
                item as ListModel.FavoriteTracks
                glide
                    .load("http://you-ps.ru/uploads/posts/2013-08/1376601606_1273.png")
                    .into(favoriteIv)
            }
        }
    }

    inner class CategoryViewHolder(
        private var view: View,
        private var glide: RequestManager
    ) : MegaViewHolder(view) {
        private val viewBinding: ItemCategoriesBinding by viewBinding(ItemCategoriesBinding::bind)
        override fun onBind(item: ListModel) {
            item as ListModel.CategoryModel
            with(viewBinding) {
                titleTv.text = item.category.name
                glide
                    .load(item.category.imageId)
                    .into(favoriteIv)
            }
        }
    }

    inner class PlaylistViewHolder(
        private var view: View,
        private var glide: RequestManager
    ) : MegaViewHolder(view) {
        private val viewBinding: ItemPlaylistBinding by viewBinding(ItemPlaylistBinding::bind)
        override fun onBind(item: ListModel) {
            item as ListModel.ListenedAlbumModel
            with(viewBinding){
                titleTv.text = item.listenedAlbum.name
                glide
                    .load(item.listenedAlbum.cover)
                    .into(favoriteIv)
            }
        }
    }

    inner class TitleViewHolder(
        private var view: View,
        private var glide: RequestManager
    ) : MegaViewHolder(view) {
        private val viewBinding: ItemTitleBinding by viewBinding(ItemTitleBinding::bind)
        override fun onBind(item: ListModel) {}
    }
}
