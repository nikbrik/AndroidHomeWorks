package com.nikbrik.permissionsanddate.recyclerview

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.TextView
import coil.load
import androidx.recyclerview.widget.RecyclerView
import com.nikbrik.permissionsanddate.databinding.LocationBinding
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter

class LocationHolder(
    private val binding: LocationBinding,
    onClickAction: (position: Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            onClickAction(bindingAdapterPosition)
        }
    }

    fun bind(
        locationData: LocationData
    ) {
        binding.imageView.load(locationData.imageLink)
        binding.data.text = locationData.toString()
        val formatter = DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy")
            .withZone(ZoneId.systemDefault())
        binding.time.text = formatter.format(locationData.timestamp)
    }

}