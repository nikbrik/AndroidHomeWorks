package com.nikbrik.lists

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nikbrik.lists.databinding.FragmentListBinding
import kotlin.random.Random

class ListFragment : Fragment(R.layout.fragment_list), NewItemDialogListener {

    private val binding: FragmentListBinding by viewBinding()
    private var productAdapter: ProductAdapter by autoCleared()
    private var startProducts: List<Product> = listOf(
        Product.Vegetable(
            "https://unsplash.com/photos/rNYCrcjUnOA/download?force=true&w=640",
            "Test item",
            "It it a random test element of the list",
        )
    )

    override fun onPositiveButtonClick(title: String, description: String) {
        productAdapter.addProduct(
            when (Random.nextInt(2)) {
                0 -> Product.Fruit("", title, description)
                1 -> Product.Vegetable("", title, description)
                else -> error("Random fun must return 0 or 1")
            },
            0,
        )
        binding.recyclerView.scrollToPosition(0)
        updateRecyclerViewPlaceholder()
    }

    private fun removeClickedItem(position: Int) {
        productAdapter.removeProduct(position)
        updateRecyclerViewPlaceholder()
    }

    private fun updateRecyclerViewPlaceholder() {
        productAdapter.apply {
            binding.recyclerViewPlaceholder.isVisible = products.isEmpty()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addElement.setOnClickListener {
            NewItemDialogFragment().show(childFragmentManager, TAG_NEW_ITEM_DIALOG)
        }
        initList()
    }

    override fun onResume() {
        super.onResume()

        if (productAdapter.products.isEmpty())
            productAdapter.updateProducts(
                run {
                    val products: MutableList<Product> = emptyList<Product>().toMutableList()
                    for (i in 1..10) {
                        products += startProducts
                    }
                    products.toList()
                }
            )
    }

    private fun initList() {
        productAdapter = ProductAdapter { position -> removeClickedItem(position) }
        binding.recyclerView.apply {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArray(KEY_PRODUCTS, productAdapter.products.toTypedArray())
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        (savedInstanceState?.getParcelableArray(KEY_PRODUCTS))?.let { parcelableArray ->
            parcelableArray.filterIsInstance<Product>()
                .takeIf { filteredArray -> filteredArray.size == parcelableArray.size }
                ?.let { productAdapter.updateProducts(it) }
        }
    }

    companion object {
        const val KEY_PRODUCTS = "KEY_PRODUCTS"
        const val TAG_NEW_ITEM_DIALOG = "TAG_NEW_ITEM_DIALOG"
    }
}
