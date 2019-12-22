package com.mvpclean.scb.ui.favoritelist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mvpclean.scb.domain.model.Mobile
import com.mvpclean.scb.ui.R


class FavoriteRecyclerAdapter :
    ListAdapter<Mobile, RecyclerView.ViewHolder>(BaseFileUploadDiffCallback()), CustomItemTouchHelperListener {

    var onItemClickListener: ((item: Mobile) -> Unit)? = null

    var onSwipeRemoveListener: ((item: Mobile,position: Int) -> Unit)? = null

    var listFavorite = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_view_favorite,
                parent, false
            )
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FavoriteViewHolder) {
            val data = getItem(position) as Mobile
            holder.bindData(position, data, onItemClickListener, listFavorite)
        }
    }

    class BaseFileUploadDiffCallback : DiffUtil.ItemCallback<Mobile>() {
        override fun areItemsTheSame(
            oldItem: Mobile, newItem: Mobile
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Mobile
            , newItem: Mobile
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemDismiss(position: Int) {
        onSwipeRemoveListener?.invoke(getItem(position),position)
        notifyItemRemoved(position)
    }
}

