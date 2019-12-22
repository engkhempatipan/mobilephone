package com.mvpclean.scb.ui.mobilelist.adapdter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mvpclean.scb.domain.model.Mobile
import com.mvpclean.scb.ui.R


class MobilesRecyclerAdapter :
    ListAdapter<Mobile, RecyclerView.ViewHolder>(BaseFileUploadDiffCallback()) {

    var onItemClickListener: ((item: Mobile) -> Unit)? = null

    var onFavoriteClickListener: ((item: Mobile) -> Unit)? = null

    var onUnFavoriteClickListener: ((item: Mobile) -> Unit)? = null

    var listFavorite = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_view_mobile,
                parent, false
            )
        return MobileViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MobileViewHolder) {
            val data = getItem(position) as Mobile
            holder.bindData(position, data, onItemClickListener, onFavoriteClickListener,onUnFavoriteClickListener,listFavorite)
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
}

