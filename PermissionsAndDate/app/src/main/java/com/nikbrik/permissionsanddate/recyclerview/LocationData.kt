package com.nikbrik.permissionsanddate.recyclerview

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.threeten.bp.Instant
import java.util.UUID

sealed class LocationData(
    // ИД
    open val id: UUID,
    // Время
    open val timestamp: Instant,
    // Изображение
    open var imageLink: String,
) : Parcelable {

    @Parcelize
    data class BaseLocationData(
        // Данные локации
        private val altitude: Double,
        private val longitude: Double,
        private val latitude: Double,
        private val speed: Float,
        private val accuracy: Float,
        override val id: UUID = UUID.randomUUID(),
        override val timestamp: Instant = Instant.now(),
        override var imageLink: String = ""
    ) : LocationData(
        id,
        timestamp,
        imageLink,
    ) {
        override fun toString(): String {
            return """
            altitude = $altitude
            longitude = $longitude
            latitude = $latitude
            speed = $speed
            accuracy = $accuracy
            """.trimIndent()
        }
    }
}
