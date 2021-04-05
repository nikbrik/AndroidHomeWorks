package com.nikbrik.newsbyviewpager

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class FilterDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.filter_dialog_title))
            .setMultiChoiceItems(
                requireArguments().getStringArray(KEY_LIST),
                requireArguments().getBooleanArray(KEY_BARRAY),
            ) { _, _, _ -> }
            .setPositiveButton(getString(R.string.apply)) { dialog, i ->
//                dialog?.
//                (requireParentFragment() as MultichoiceDialogParent).useOfItems
//                getDialog()
//                dialog.dismiss()
            }
            .setNegativeButton(getString(R.string.cancel)) { dialog, i ->

            }
            .create()
    }

    companion object {
        const val KEY_LIST = "stringArray"
        const val KEY_BARRAY = "boolArray"
    }
}
