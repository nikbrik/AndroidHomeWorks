package com.nikbrik.newsbyviewpager

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class FilterDialogFragment : DialogFragment() {

    private var checkArray: BooleanArray? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        checkArray = requireArguments().getBooleanArray(KEY_BARRAY)

        return AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.filter_dialog_title))
            .setMultiChoiceItems(
                ArticleTag.getStringArray(),
                checkArray,
            ) { _, which, isChecked ->
                checkArray?.set(which, isChecked)
            }
            .setPositiveButton(getString(R.string.apply)) { dialog, i ->
                (requireParentFragment() as MultichoiceDialogListener).onMultichoiceDialogApply(checkArray)
                dialog.dismiss()
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
