package com.nikbrik.filesapp.data

import android.content.Context
import android.util.Log
import com.nikbrik.filesapp.network.Networking
import java.io.File
import java.time.Instant

class FileRepository(private val context: Context) {

    @Suppress("BlockingMethodInNonBlockingContext")
    suspend fun downloadFilesFromAssetsLinks() {
        context.assets.open("links.txt")
            .bufferedReader()
            .use {
                it.readText().lineSequence().forEach { uri ->
                    createAndDownloadFile(uri)
                }
            }
    }

    suspend fun createAndDownloadFile(uri: String): File {
        val remoteFileName = File(uri).name
        val file = createFileInExtStorage(remoteFileName)
        downloadToFileOrDeleteIfFail(file, uri)
        return file
    }

    private fun createFileInExtStorage(fileName: String): File {
        val dir = context.getExternalFilesDir("testDir")
        val timeStamp = Instant.now().epochSecond
        return File(dir, "${timeStamp}_$fileName")
    }

    private suspend fun downloadToFileOrDeleteIfFail(file: File, uri: String) {
        try {
            file.outputStream().buffered().use { fileOutputStream ->
                val responseBody = Networking.githubApi.getFile(uri)
                responseBody.byteStream()
                    .use { inputStream ->
                        inputStream.copyTo(fileOutputStream)
                    }
            }
        } catch (t: Throwable) {
            Log.e("nikbrik", t.message, t)
            file.delete()
        }
    }
}
