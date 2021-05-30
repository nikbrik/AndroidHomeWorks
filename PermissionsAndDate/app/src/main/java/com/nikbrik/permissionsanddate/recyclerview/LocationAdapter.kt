package com.nikbrik.permissionsanddate.recyclerview

import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.nikbrik.permissionsanddate.BaseLocationAdapterDelegate

class LocationAdapter(
    onClickAction: (position: Int) -> Unit,
) : AsyncListDifferDelegationAdapter<LocationData>(LocationDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(BaseLocationAdapterDelegate(onClickAction))
    }

    fun updateLocations(locationData: List<LocationData>) {
        differ.submitList(locationData.toList())
    }

    class LocationDiffUtilCallback : DiffUtil.ItemCallback<LocationData>() {
        override fun areItemsTheSame(oldItem: LocationData, newItem: LocationData): Boolean {
            return oldItem::class == newItem::class && oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: LocationData, newItem: LocationData): Boolean {
            return oldItem == newItem
        }

    }
}