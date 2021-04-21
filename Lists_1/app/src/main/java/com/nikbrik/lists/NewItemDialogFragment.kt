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
                titleInputLayout.error = getString(R.string.empty_title_error)
                    .takeIf { newTitle.text?.isBlank() == true }
                descriptionInputLayout.error = getString(R.string.empty_description_error)
                    .takeIf { newDescription.text?.isBlank() == true }

                if (titleInputLayout.error == null && descriptionInputLayout.error == null) {

                    // Передача данных в родительский фрагмент для обработки ввода
                    (requireParentFragment() as NewItemDialogListener).OnPositiveButtonClick(
                        newTitle.text.toString(),
                        newDescription.text.toString(),
                    )
                    dialog?.cancel()
                }
            }

            cancelButton.setOnClickListener {
                dialog?.cancel()
            }
        }
    }
}
