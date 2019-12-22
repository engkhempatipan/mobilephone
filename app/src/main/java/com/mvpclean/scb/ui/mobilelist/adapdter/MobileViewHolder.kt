package com.mvpclean.scb.ui.mobilelist.adapdter

import android.annotation.SuppressLint
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mvpclean.scb.domain.model.Mobile
import com.mvpclean.scb.ui.R
import kotlinx.android.synthetic.main.item_view_mobile.view.*

class MobileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    @SuppressLint("SetTextI18n")
    fun bindData(
        position: Int,
        item: Mobile,
        listener: ((item: Mobile) -> Unit)?,
        favoriteClickListener: ((item: Mobile) -> Unit)?,
        onUnFavoriteClickListener: ((item: Mobile) -> Unit)?,
        listFavorite: List<String>
    ) = with(itemView) {

        text_view_title?.text = item.name
        text_view_description?.text = item.description
        text_view_price?.text = resources.getString(R.string.text_price) + item.price
        text_view_rating?.text = resources.getString(R.string.text_rating) + item.rating

        Glide.with(this)
            .load(item.thumbImageURL)
            .into(image_view_thumbnail)

        itemView.setOnClickListener {
            listener?.invoke(item)
        }

        if (listFavorite.contains(item.id)) {
            image_view_favorite.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_heart_svg))

            image_view_favorite?.setOnClickListener {
                onUnFavoriteClickListener?.invoke(item)
            }

        } else {
            image_view_favorite.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_heart_svg))

            image_view_favorite.setOnClickListener {
                favoriteClickListener?.invoke(item)
            }
        }


    }

    private fun isFavorite() {

    }

}