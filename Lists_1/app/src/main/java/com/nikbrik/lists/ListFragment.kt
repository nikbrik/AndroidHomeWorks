package com.nikbrik.lists

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nikbrik.lists.databinding.FragmentListBinding

class ListFragment : Fragment(R.layout.fragment_list), NewItemDialogListener {

    private val binding: FragmentListBinding by viewBinding()
    private var products: Array<Product> = emptyArray()

    override fun OnPositiveButtonClisk(title: String, description: String) {
//        TODO("Add new item in the list")
        Toast.makeText(context, "add new", Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addElement.setOnClickListener {
            NewItemDialogFragment().show(childFragmentManager, TAG_NEW_ITEM_DIALOG)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArray(KEY_PRODUCTS, products)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let { products = it.getParcelableArray(KEY_PRODUCTS) as Array<Product> }
    }

    companion object {
        const val KEY_PRODUCTS = "KEY_PRODUCTS"
        const val TAG_NEW_ITEM_DIALOG = "TAG_NEW_ITEM_DIALOG"
    }
}