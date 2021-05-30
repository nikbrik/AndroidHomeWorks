package com.nikbrik.permissionsanddate

import com.nikbrik.permissionsanddate.recyclerview.LocationData

interface LocationDataRepository {
    val locationData: MutableList<LocationData>
    var isRestored: Boolean
}