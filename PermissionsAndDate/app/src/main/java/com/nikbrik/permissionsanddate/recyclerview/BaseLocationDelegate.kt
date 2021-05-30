package com.nikbrik.permissionsanddate

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.nikbrik.permissionsanddate.databinding.LocationBinding
import com.nikbrik.permissionsanddate.recyclerview.LocationData
import com.nikbrik.permissionsanddate.recyclerview.LocationHolder

class BaseLocationAdapterDelegate(
    private val onClickAction: (position: Int) -> Unit,
) :
    AbsListItemAdapterDelegate<LocationData.BaseLocationData, LocationData, LocationHolder>() {
    override fun isForViewType(
        item: LocationData,
        items: MutableList<LocationData>,
        position: Int
    ): Boolean {
        return item is LocationData.BaseLocationData
    }

    override fun onCreateViewHolder(parent: ViewGroup): LocationHolder {
        return LocationHolder(
            LocationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClickAction
        )
    }

    override fun onBindViewHolder(
        item: LocationData.BaseLocationData,
        holder: LocationHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }
}
