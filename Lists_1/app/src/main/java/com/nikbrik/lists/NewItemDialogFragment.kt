package com.nikbrik.lists

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nikbrik.lists.databinding.DialogNewItemBinding

class NewItemDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setView(R.layout.dialog_new_item)
            .setNegativeButton(getString(R.string.cancel_button_title)) { dialog, _ ->
                dialog.cancel()
            }
            .setPositiveButton(getString(R.string.add_button_title)) { dialog, _ ->
//                dialog.
                getDialog()?.apply {
                    val newTitle = findViewById<EditText>(R.id.new_title)
                    val newDescription = findViewById<EditText>(R.id.new_description)
                    (requireParentFragment() as NewItemDialogListener).OnPositiveButtonClisk(
                        newTitle.text.toString(),
                        newDescription.text.toString(),
                    )
                }
            }
            .create()
    }
}