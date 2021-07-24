package com.nikbrik.networking

import android.app.Application
import androidx.viewbinding.BuildConfig
import timber.log.Timber

class NetworkingApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // loging
        if (BuildConfig.DEBUG && Timber.treeCount() == 0) Timber.plant(Timber.DebugTree())
    }
}
