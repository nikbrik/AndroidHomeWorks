package com.nikbrik.hw19.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nikbrik.hw19.R

abstract class ProductHolder(
    view: View,
    onClickAction: (position: Int) -> Unit,
    OnLongClickAction: (position: Int) -> Unit
) : RecyclerView.ViewHolder(view) {
    lateinit var photo: ImageView
    lateinit var title: TextView
    lateinit var description: TextView

    init {
        view.setOnLongClickListener {
            OnLongClickAction(bindingAdapterPosition)
            true
        }
        view.setOnClickListener {
            onClickAction(bindingAdapterPosition)
        }
    }

    fun bind(
        photoLink: String,
        title: String,
        description: String,
        @DrawableRes placeHolderId: Int,
    ) {
        this.title.text = title
        this.description.text = description
        Glide.with(itemView)
            .load(if (photoLink.isBlank()) placeHolderId else photoLink)
            .placeholder(placeHolderId)
            .error(R.drawable.no_image_available)
            .into(this.photo)
    }

    fun bindGrid(
        photoLink: String,
        @DrawableRes placeHolderId: Int,
    ) {
        Glide.with(itemView)
            .load(if (photoLink.isBlank()) placeHolderId else photoLink)
            .placeholder(placeHolderId)
            .error(R.drawable.no_image_available)
            .into(this.photo)
    }
}
