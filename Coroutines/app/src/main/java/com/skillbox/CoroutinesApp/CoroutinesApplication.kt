package com.skillbox.CoroutinesApp

import android.app.Application
import timber.log.Timber

class CoroutinesApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG && Timber.treeCount() == 0) Timber.plant(Timber.DebugTree())
    }
}
