package com.nikbrik.permissionsanddate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nikbrik.permissionsanddate.recyclerview.LocationData

class MainActivity : AppCompatActivity(), LocationDataRepository {

    override var locationData = mutableListOf<LocationData>()
    override var isRestored = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.apply {
            findFragmentById(R.id.container) ?: beginTransaction()
                .add(R.id.container, PermissionRequestFragment())
                .commit()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArray(KEY_LOCATION_DATA, locationData.toTypedArray())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        (savedInstanceState.getParcelableArray(KEY_LOCATION_DATA))?.let { parcelableArray ->
            isRestored = true
            parcelableArray.filterIsInstance<LocationData>()
                .takeIf { filteredArray -> filteredArray.size == parcelableArray.size }
                ?.let { list ->
                    locationData.addAll(list)
                }
        }
    }

    companion object {
        const val KEY_LOCATION_DATA = "KEY_LOCATION_DATA"
    }
}
