package com.nikbrik.permissionsanddate

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nikbrik.permissionsanddate.databinding.FragmentPermissionRequestBinding

class PermissionRequestFragment : Fragment(R.layout.fragment_permission_request) {
    private val binding: FragmentPermissionRequestBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkPermissions()
    }

    private fun checkPermissions() {
        val isLocationPermissionGranted = ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        processRequestResult(isLocationPermissionGranted)
    }

    private fun processRequestResult(isLocationPermissionGranted: Boolean) {
        if (isLocationPermissionGranted) {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, LocationListFragment())
                .commit()

        } else {
            toast(getString(R.string.need_permissions))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.permissionRequestButton.setOnClickListener {
            requestLocationPermission()
        }
    }

    // Контракт для запроса разрешений
    private val requestMultiplePermissions =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            Manifest.permission.ACCESS_FINE_LOCATION
            permissions.forEach {
                val isGranted = it.value
                when (it.key) {
                    Manifest.permission.ACCESS_FINE_LOCATION -> {
                        processRequestResult(isGranted)
                    }
                }
            }
        }

    private fun requestLocationPermission() {
        requestMultiplePermissions.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
            )
        )
    }


}