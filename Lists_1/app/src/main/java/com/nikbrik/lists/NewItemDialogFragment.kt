package com.nikbrik.lists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nikbrik.lists.databinding.DialogNewItemBinding

class NewItemDialogFragment : DialogFragment() {

    private val binding: DialogNewItemBinding by viewBinding(R.id.dialog_root_container)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_new_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            addButton.setOnClickListener {

                // Проверка на заполнение
                titleInputLayout.error =
                    if (newTitle.text?.isBlank() ?: false) "Title is empty" else null
                descriptionInputLayout.error =
                    if (newDescription.text?.isBlank() ?: false) "Description is empty" else null

                if (newTitle.text?.isNotBlank() ?: false && newDescription.text?.isNotBlank() ?: false) {

                    // Передача данных в родительский фрагмент для обработки ввода
                    (requireParentFragment() as NewItemDialogListener).OnPositiveButtonClisk(
                        newTitle.text.toString(),
                        newDescription.text.toString(),
                    )
                }
            }

            cancelButton.setOnClickListener {
                dialog?.cancel()
            }
        }
    }

}