package com.nikbrik.permissionsanddate

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import timber.log.Timber

class PermissionAndDateApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Подключаем timber
        if (BuildConfig.DEBUG && Timber.treeCount() == 0) Timber.plant(Timber.DebugTree())
        // Подключаем библиотеку для работы с датой
        AndroidThreeTen.init(this)
    }
}