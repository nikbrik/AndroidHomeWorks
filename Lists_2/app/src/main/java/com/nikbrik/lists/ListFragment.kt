package com.nikbrik.lists

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nikbrik.lists.databinding.FragmentListBinding
import jp.wasabeef.recyclerview.animators.ScaleInAnimator
import kotlin.random.Random

class ListFragment : Fragment(R.layout.fragment_list), NewItemDialogListener {

    private val binding: FragmentListBinding by viewBinding()
    private var productAdapter: ProductAdapter by autoCleared()
    private var isRestored = false
    private var layoutManagerType = TYPE_LINEAR

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
            binding.recyclerViewPlaceholder.isVisible = differ.currentList.isEmpty()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.getInt(KEY_LAYOUT_MANAGER_TYPE)?.let { layoutManagerType = it }
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

        if (isRestored.not())
            productAdapter.updateProducts(
                run {
                    val products: MutableList<Product> = emptyList<Product>().toMutableList()
                    for (i in 1..20) {
                        products += Product.Vegetable(
                            "https://unsplash.com/photos/rNYCrcjUnOA/download?force=true&w=640",
                            "Test item",
                            "It it a random test element of the list",
                        )
                    }
                    products.toList()
                }
            )
        updateRecyclerViewPlaceholder()
    }

    private fun initList() {
        productAdapter = ProductAdapter { position -> removeClickedItem(position) }
        binding.recyclerView.apply {
            adapter = productAdapter
            layoutManager = when (layoutManagerType) {
                TYPE_LINEAR -> LinearLayoutManager(requireContext())
                TYPE_GRID -> GridLayoutManager(requireContext(), 3)
                TYPE_STAGGERED_GRID -> StaggeredGridLayoutManager(
                    3,
                    StaggeredGridLayoutManager.VERTICAL
                )
                else -> LinearLayoutManager(requireContext())
            }
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.HORIZONTAL
                )
            )

            itemAnimator = ScaleInAnimator()

            setHasFixedSize(true)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArray(KEY_PRODUCTS, productAdapter.differ.currentList.toTypedArray())
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        (savedInstanceState?.getParcelableArray(KEY_PRODUCTS))?.let { parcelableArray ->
            isRestored = true
            parcelableArray.filterIsInstance<Product>()
                .takeIf { filteredArray -> filteredArray.size == parcelableArray.size }
                ?.let { productAdapter.updateProducts(it) }
        }
    }

    companion object {
        const val KEY_PRODUCTS = "KEY_PRODUCTS"
        const val TAG_NEW_ITEM_DIALOG = "TAG_NEW_ITEM_DIALOG"
        const val KEY_LAYOUT_MANAGER_TYPE = "KEY_LAYOUT_MANAGER_TYPE"
        const val TYPE_LINEAR = 1
        const val TYPE_GRID = 2
        const val TYPE_STAGGERED_GRID = 3
    }
}
