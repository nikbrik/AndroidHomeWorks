package com.nikbrik.permissionsanddate

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.LocationServices.getFusedLocationProviderClient
import com.jakewharton.threetenabp.AndroidThreeTen
import com.nikbrik.permissionsanddate.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()

    private val requestMultiplePermissions =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            permissions.forEach {
                when (it.key) {
                    ACCESS_FINE_LOCATION -> {
                        if (it.value.not()) toast("not granted")
                    }
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (BuildConfig.DEBUG && Timber.treeCount() == 0) Timber.plant(Timber.DebugTree())

        val client = getFusedLocationProviderClient(this)
        GoogleApiAvailability.getInstance()
            .checkApiAvailability(client)
            .onSuccessTask { _ -> client.lastLocation }
            .addOnFailureListener { _ -> Timber.d("Location unavailable.") }

        AndroidThreeTen.init(this)

        binding.requestPermission.setOnClickListener {
            requestLocationPermission()
        }

        val isLocationPermissionGranted =
            ActivityCompat.checkSelfPermission(
                this,
                ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED

        if (isLocationPermissionGranted.not())
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.location_permission_placeholder_title))
                .setNeutralButton("OK") { _, _ ->
                    requestLocationPermission()
                }.show()
    }

    fun requestLocationPermission() {
        requestMultiplePermissions.launch(
            arrayOf(
                ACCESS_FINE_LOCATION,
            )
        )
    }
}
