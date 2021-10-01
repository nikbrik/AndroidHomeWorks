package com.nikbrik.filesapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DataStoreSettings.DATASTORE_NAME)

class DataStoreSettings(context: Context) : ISettings {

    private val dataStore = context.dataStore

    override suspend fun isFirstRun(): Boolean {
        return dataStore.data.map { settings ->
            settings[booleanPreferencesKey(FIRST_RUN_KEY)] ?: true
        }.first()
    }

    override suspend fun setFirstRunFalse() {
        dataStore.edit { settings ->
            settings[booleanPreferencesKey(FIRST_RUN_KEY)] = false
        }
    }

    override suspend fun isFileCreated(key: String): Boolean {
        return dataStore.data.map { settings ->
            settings[stringPreferencesKey(key)].isNullOrBlank().not()
        }.first()
    }

    override suspend fun putFileName(
        key: String,
        fileName: String
    ) {
        dataStore.edit { settings ->
            settings[stringPreferencesKey(key)] = fileName
        }
    }

    companion object {
        const val DATASTORE_NAME = "datastore"
        const val FIRST_RUN_KEY = "FIRST_RUN_KEY"
    }
}
