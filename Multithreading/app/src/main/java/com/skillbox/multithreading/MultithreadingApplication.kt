package com.skillbox.multithreading

import android.app.Application
import timber.log.Timber
import java.util.concurrent.Executors

class MultithreadingApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG && Timber.treeCount() == 0) Timber.plant(Timber.DebugTree())
    }

    companion object {
        val executorService =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
    }
}
