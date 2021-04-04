package com.nikbrik.newsbyviewpager

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class FilterDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.filter_dialog_title))
            .setMultiChoiceItems(
                arrayOf(""),
                booleanArrayOf(true)
            ) { dialog, which, isChecked ->

            }
            .create()
    }

}