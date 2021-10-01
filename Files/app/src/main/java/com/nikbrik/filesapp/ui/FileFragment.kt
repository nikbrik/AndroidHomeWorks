package com.nikbrik.filesapp.ui

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nikbrik.filesapp.R
import com.nikbrik.filesapp.data.DataStoreSettings
import com.nikbrik.filesapp.data.SharedPreferenceSettings
import com.nikbrik.filesapp.databinding.FragmentFileBinding
import com.nikbrik.filesapp.extensions.toastToMainThread

class FileFragment : Fragment(R.layout.fragment_file) {

    private val binding: FragmentFileBinding by viewBinding()
    private val viewModel: MainViewModel by activityViewModels()
    private val args: FileFragmentArgs by navArgs()
    private val settings
        get() =
            if (args.settingsType == SHARED_PREFERENCE_SETTINGS) {
                SharedPreferenceSettings(requireContext())
            } else {
                DataStoreSettings(requireContext())
            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.downloadFilesFromAssetsLinks(settings)

        binding.button.setOnClickListener {
            val text = binding.editText.text
            if (text.isNotBlank()) {
                if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
                    setProgressView(true)
                    val uri = text.toString()
                    viewModel.createAndDownloadFile(settings, uri) {
                        setProgressView(false)
                    }
                } else {
                    toastToMainThread(getString(R.string.ext_stor_not_mounted))
                }
            }
        }

        observe()
    }

    private fun observe() {
        viewModel.errorMessage.observe(
            viewLifecycleOwner,
            {
                Log.e("ERROR", it.message, it)
            }
        )
        viewModel.toast.observe(
            viewLifecycleOwner,
            {
                toastToMainThread(it)
            }
        )
    }

    private fun setProgressView(inProgress: Boolean) {
        binding.button.isVisible = inProgress.not()
        binding.progressBar.isVisible = inProgress
    }
}
