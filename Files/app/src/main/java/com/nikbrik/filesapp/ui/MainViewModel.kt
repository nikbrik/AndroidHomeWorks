package com.nikbrik.filesapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.nikbrik.filesapp.R
import com.nikbrik.filesapp.data.FileRepository
import com.nikbrik.filesapp.data.ISettings
import com.nikbrik.filesapp.extensions.SingleLiveEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val errorEvent = SingleLiveEvent<Throwable>()
    val errorMessage: LiveData<Throwable>
        get() = errorEvent
    private val toastEvent = SingleLiveEvent<String>()
    val toast: LiveData<String>
        get() = toastEvent
    private val repository = FileRepository(getApplication())
    private val context get() = getApplication<Application>()

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        errorEvent.postValue(throwable)
    }

    fun createAndDownloadFile(settings: ISettings, uri: String, callback: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO + errorHandler) {
            if (settings.isFileCreated(uri)) {
                toastEvent.postValue(context.getString(R.string.file_created))
            } else {
                val file = repository.createAndDownloadFile(uri)
                if (file.exists()) {
                    settings.putFileName(uri, file.name)
                    toastEvent.postValue(
                        context.getString(
                            R.string.file_downloaded,
                            File(uri).name
                        )
                    )
                }
            }
            withContext(Dispatchers.Main) { run(callback) }
        }
    }

    fun downloadFilesFromAssetsLinks(settings: ISettings) {
        viewModelScope.launch(Dispatchers.IO + errorHandler) {
            if (settings.isFirstRun()) {
                settings.setFirstRunFalse()
                repository.downloadFilesFromAssetsLinks()
            }
        }
    }
}
