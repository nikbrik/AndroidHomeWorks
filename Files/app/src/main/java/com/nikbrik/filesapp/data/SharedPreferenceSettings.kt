package com.nikbrik.filesapp.data

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceSettings(private val context: Context) : ISettings {

    private val sharedPreference: SharedPreferences by lazy {
        context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
    }

    override suspend fun isFirstRun() = sharedPreference.getBoolean(FIRST_RUN_KEY, true)

    override suspend fun setFirstRunFalse() {
        sharedPreference.edit()
            .putBoolean(FIRST_RUN_KEY, false)
            .apply()
    }

    override suspend fun isFileCreated(key: String) = sharedPreference.getString(key, null) != null

    override suspend fun putFileName(
        key: String,
        fileName: String
    ) {
        sharedPreference.edit()
            .putString(key, fileName)
            .apply()
    }

    companion object {
        const val SHARED_PREFERENCES_KEY = "SHARED_PREFERENCES_KEY"
        const val FIRST_RUN_KEY = "FIRST_RUN_KEY"
    }
}
