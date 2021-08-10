package com.nikbrik.moshiHW.app

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.nikbrik.moshiHW.R
import com.nikbrik.moshiHW.databinding.FragmentSearchBinding
import com.nikbrik.moshiHW.mvvm.NetworkingViewModel

class SearchFragment : Fragment(R.layout.fragment_search) {
    private var binding: FragmentSearchBinding? = null
    private val viewModel: NetworkingViewModel by activityViewModels()
    private val args: SearchFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        restoreViewData()

        // Если фрагмент открыт из другого с целью повторить запрос
        if (args.repeat) search()

        binding?.search?.setOnClickListener {
            search()
        }
    }

    private fun restoreViewData() {
        restoreTypes()
        restoreFields()
    }

    private fun restoreFields() {
        // Восстановление значений для полей
        val restoredType = viewModel.searchType.ifBlank {
            viewModel.searchTypeArray.lastOrNull() ?: ""
        }
        binding?.apply {
            autoCompleteText.setText(restoredType, false)
            name.setText(viewModel.searchName)
            year.setText(viewModel.searchYear)
        }
    }

    private fun restoreTypes() {
        // Получение списка типов
        if (viewModel.searchTypeArray.isEmpty()) {
            viewModel.searchTypeArray = resources.getStringArray(R.array.items)
        }
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, viewModel.searchTypeArray)
        binding?.autoCompleteText?.setAdapter(adapter)
    }

    private fun search() {

        setScreenState(false)

        saveViewData()

        binding?.apply {
            viewModel.onSearchButtonClickEvent(
                name.text.toString(),
                year.text.toString(),
                autoCompleteText.text.toString(),
            ) { errorMessage ->
                // Действие навигации нужно выполнить на главном потоке
                Handler(Looper.getMainLooper()).post {
                    findNavController().navigate(
                        SearchFragmentDirections.actionSearchFragmentToListFragment(
                            errorMessage
                        )
                    )
                }
            }
        }

        setScreenState(true)
    }

    private fun saveViewData() {
        binding?.apply {
            viewModel.searchName = name.text.toString()
            viewModel.searchYear = year.text.toString()
            viewModel.searchType = autoCompleteText.text.toString()
        }
    }

    private fun setScreenState(state: Boolean) {
        binding?.apply {
            search.isVisible = state
            progressBar.isVisible = !state
            exposedMenu.isEnabled = state
            name.isEnabled = state
            year.isEnabled = state
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        restoreTypes()
    }
}
