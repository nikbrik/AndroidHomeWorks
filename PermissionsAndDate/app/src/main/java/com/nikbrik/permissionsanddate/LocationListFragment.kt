package com.nikbrik.permissionsanddate

import android.Manifest
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.nikbrik.permissionsanddate.databinding.FragmentLocationListBinding
import com.nikbrik.permissionsanddate.recyclerview.LocationAdapter
import com.nikbrik.permissionsanddate.recyclerview.LocationData
import org.threeten.bp.Duration
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import kotlin.random.Random

class LocationListFragment : Fragment(R.layout.fragment_location_list) {
    private val binding: FragmentLocationListBinding by viewBinding()
    private var locationAdapter: LocationAdapter by autoCleared()
    private val locationData: MutableList<LocationData>
        get() = (requireActivity() as LocationDataRepository).locationData
    private var isRestored: Boolean
        get() = (requireActivity() as LocationDataRepository).isRestored
        set(value) {
            (requireActivity() as LocationDataRepository).isRestored = value
        }

    // location
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private var requestingLocationUpdates = true
    private lateinit var locationCallback: LocationCallback

    // datetime
    private var dateDialog: DatePickerDialog? = null
    private var timeDialog: TimePickerDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                addNewLocation(locationResult.lastLocation)
            }
        }

        locationRequest = LocationRequest.create().apply {
            interval = Duration.ofSeconds(10).toMillis()
            fastestInterval = Duration.ofSeconds(5).toMillis()
            maxWaitTime = Duration.ofMinutes(2).toMillis()
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }

    private fun doIfGoogleApiAvailable(action: () -> Unit) {
        GoogleApiAvailability.getInstance()
            .checkApiAvailability(fusedLocationClient)
            .addOnSuccessListener { run(action) }
    }

    @SuppressLint("MissingPermission")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initList()
        updateRecyclerViewPlaceholder()

        binding.locationRequestButton.setOnClickListener {
            doIfGoogleApiAvailable {
                checkPermissions()
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location -> addNewLocation(location) }
            }
        }

        val result = GoogleApiAvailability.getInstance()
            .isGooglePlayServicesAvailable(requireContext())
        GoogleApiAvailability.getInstance()
            .getErrorDialog(this, result, 1)
            ?.apply {
                when (result) {
                    ConnectionResult.SUCCESS -> {
                        setCancelable(true)
                        binding.locationRequestButton.isEnabled = true
                        binding.locationList.isEnabled = true
                    }
                    ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED -> {
                        setCancelable(true)
                        binding.locationRequestButton.isEnabled = false
                        binding.locationList.isEnabled = false
                    }
                    else -> {
                        setCancelable(false)
                        binding.locationRequestButton.isEnabled = false
                        binding.locationList.isEnabled = false
                    }
                }
            }
            ?.show()
    }

    private fun checkPermissions() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            parentFragmentManager.apply {
                beginTransaction()
                    .replace(R.id.container, PermissionRequestFragment())
                    .commit()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (isRestored) {
            isRestored = false
            locationAdapter.updateLocations(locationData)
            updateRecyclerViewPlaceholder()
        }
        doIfGoogleApiAvailable {
            checkPermissions()
            if (requestingLocationUpdates) startLocationUpdates()
        }
    }

    override fun onPause() {
        super.onPause()
        checkPermissions()
        stopLocationUpdates()
    }

    private fun stopLocationUpdates() {
        requestingLocationUpdates = true
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        requestingLocationUpdates = false

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    private fun addNewLocation(location: Location?) {

        location?.run {
            locationData.add(
                LocationData.BaseLocationData(
                    altitude,
                    longitude,
                    latitude,
                    speed,
                    accuracy,
                ).apply {
                    imageLink = randomImageURL()
                }
            )
        }
        locationAdapter.updateLocations(locationData)
        updateRecyclerViewPlaceholder()
    }

    private fun randomImageURL(): String {
        return when (Random.nextInt(5)) {
            0 -> "https://unsplash.com/photos/OD9EOzfSOh0/download?force=true&w=640"
            1 -> "https://unsplash.com/photos/_96hJVoWwQE/download?force=true&w=640"
            2 -> "https://unsplash.com/photos/8qg-hy6VbYE/download?force=true&w=640"
            3 -> "https://unsplash.com/photos/njGrQxgsp5Y/download?force=true&w=640"
            4 -> "https://unsplash.com/photos/JoRoy500nCc/download?force=true&w=640"
            else -> ""
        }
    }

    private fun initList() {
        locationAdapter = LocationAdapter(this::onClickAction)
        binding.locationList.apply {
            adapter = locationAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        locationAdapter.updateLocations(locationData)
    }

    private fun updateRecyclerViewPlaceholder() {
        binding.recyclerViewPlaceholder.isVisible = locationData.isEmpty()
    }

    private fun onClickAction(position: Int) {

        // Текущий элемент датасета
        val currentItem = locationData[position]

        // дата и время текущего элемента
        val itemDateTime = LocalDateTime.ofInstant(currentItem.timestamp, ZoneId.systemDefault())

        // Вызываем диалог для установки даты и сохраняем ссылку для возможности завершить
        dateDialog = DatePickerDialog(
            requireContext(),
            { _, year, month, day ->
                // Вызываем диалог для установки времени и сохраняем ссылку для возможности завершить
                timeDialog = TimePickerDialog(
                    requireContext(),
                    { _, hour, minute ->

                        // Компонуем дату
                        val zonedDateTime = LocalDateTime.of(year, month + 1, day, hour, minute)
                            .atZone(ZoneId.systemDefault())

                        // Делаем копию элемента, чтобы корректно отработало обновление списка
                        when (currentItem) {
                            is LocationData.BaseLocationData -> {
                                // Обновляем дату в копии текущего элемента
                                locationData[position] = currentItem.copy(
                                    timestamp = zonedDateTime.toInstant()
                                )
                            }
                        }

                        // Обновляем список
                        locationAdapter.updateLocations(locationData)
                    },
                    itemDateTime.hour,
                    itemDateTime.minute,
                    true
                )
                timeDialog?.show()
            },
            itemDateTime.year,
            itemDateTime.monthValue - 1,
            itemDateTime.dayOfMonth
        )
        dateDialog?.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        timeDialog?.dismiss()
        dateDialog?.dismiss()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(REQUESTING_LOCATION_UPDATES_KEY, requestingLocationUpdates)
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        // Update the value of requestingLocationUpdates from the Bundle.
        if (savedInstanceState?.keySet()?.contains(REQUESTING_LOCATION_UPDATES_KEY) == true) {
            requestingLocationUpdates = savedInstanceState.getBoolean(
                REQUESTING_LOCATION_UPDATES_KEY
            )
        }
    }

    companion object {
        const val REQUESTING_LOCATION_UPDATES_KEY = "REQUESTING_LOCATION_UPDATES_KEY"
    }
}
