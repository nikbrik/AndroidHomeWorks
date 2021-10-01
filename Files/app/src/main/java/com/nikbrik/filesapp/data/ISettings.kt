package com.nikbrik.filesapp.data

interface ISettings {
    suspend fun isFirstRun(): Boolean
    suspend fun setFirstRunFalse()
    suspend fun isFileCreated(key: String): Boolean
    suspend fun putFileName(
        key: String,
        fileName: String
    )
}
