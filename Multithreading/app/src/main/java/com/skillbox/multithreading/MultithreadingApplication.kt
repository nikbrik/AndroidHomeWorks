package com.skillbox.multithreading

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import timber.log.Timber
import java.util.concurrent.Executors

class MultithreadingApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG && Timber.treeCount() == 0) Timber.plant(Timber.DebugTree())
        AndroidThreeTen.init(this)
    }

    companion object {
        val executorService =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
    }
}
