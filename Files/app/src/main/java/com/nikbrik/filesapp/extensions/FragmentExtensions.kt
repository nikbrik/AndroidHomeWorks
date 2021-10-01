package com.nikbrik.filesapp.extensions

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.fragment.app.Fragment

fun <T : Fragment> T.toast(text: String) {
    Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
}

fun <T : Fragment> T.toastToMainThread(text: String) {
    Handler(Looper.getMainLooper()).post {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }
}

fun <T : Fragment> T.withArguments(putAction: () -> Unit) {
    arguments.apply {
        run(putAction)
    }
}
