package com.nikbrik.lists

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nikbrik.lists.adapters.EndlessRecyclerViewScrollListener
import com.nikbrik.lists.adapters.ProductAdapter
import com.nikbrik.lists.databinding.FragmentListBinding
import jp.wasabeef.recyclerview.animators.ScaleInAnimator
import kotlin.random.Random

const val TYPE_LINEAR = 1
const val TYPE_GRID = 2
const val TYPE_STAGGERED_GRID = 3

class ListFragment : Fragment(R.layout.fragment_list), NewItemDialogListener {

    private val binding: FragmentListBinding by viewBinding()
    private var productAdapter: ProductAdapter by autoCleared()
    private var isRestored = false
    private var layoutManagerType = TYPE_LINEAR

    private fun createNewList(products: MutableList<Product>): List<Product> {
        for (i in 1..5) {
            products += Product.Fruit(
                getString(R.string.apticot_link),
                getString(R.string.apricot_title),
                getString(R.string.apricot_description),
            )
            products += Product.Vegetable(
                getString(R.string.corn_link),
                getString(R.string.corn_title),
                getString(R.string.corn_description),
            )
            products += Product.Fruit(
                getString(R.string.avocado_link),
                getString(R.string.avocado_title),
                getString(R.string.avocado_description),
            )
            products += Product.Vegetable(
                getString(R.string.squash_link),
                getString(R.string.squash_title),
                getString(R.string.squash_description),
            )
            products += Product.Fruit(
                getString(R.string.lemon_link),
                getString(R.string.lemon_title),
                getString(R.string.lemon_description),
            )
            products += Product.Vegetable(
                getString(R.string.potato_link),
                getString(R.string.potato_title),
                getString(R.string.potato_description),
            )
            products += Product.Fruit(
                getString(R.string.peach_link),
                getString(R.string.peach_title),
                getString(R.string.peach_description),
            )
            products += Product.Vegetable(
                getString(R.string.radish_link),
                getString(R.string.radish_title),
                getString(R.string.radish_description),
            )
        }
        return products.toList()
    }

    override fun onPositiveButtonClick(title: String, description: String) {
        productAdapter.addProduct(
            when (Random.nextInt(2)) {
                0 -> Product.Fruit("", title, description)
                1 -> Product.Vegetable("", title, description)
                else -> error("Random fun must return 0 or 1")
            },
            0,
        )
        binding.recyclerView.smoothScrollToPosition(0)
        updateRecyclerViewPlaceholder()
    }

    private fun removeClickedItem(position: Int) {
        productAdapter.removeProduct(position)
        updateRecyclerViewPlaceholder()
    }

    private fun updateRecyclerViewPlaceholder() {
        productAdapter.apply {
            binding.recyclerViewPlaceholder.isVisible = isEmpty
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
            productAdapter.updateProducts(createNewList(emptyList<Product>().toMutableList()))
        updateRecyclerViewPlaceholder()
    }

    private fun initList() {
        productAdapter = ProductAdapter(layoutManagerType) { position -> removeClickedItem(position) }
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
//            addItemDecoration(
//                DividerItemDecoration(
//                    requireContext(),
//                    DividerItemDecoration.VERTICAL
//                )
//            )
//            addItemDecoration(
//                DividerItemDecoration(
//                    requireContext(),
//                    DividerItemDecoration.HORIZONTAL
//                )
//
            addItemDecoration(CustomItemDecoration())
            itemAnimator = ScaleInAnimator()

            setHasFixedSize(true)

            layoutManager?.let {
                addOnScrollListener(object : EndlessRecyclerViewScrollListener(it) {
                    override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                        if (totalItemsCount < 100) {
                            val newList = createNewList(productAdapter.currentList.toMutableList())
                            productAdapter.updateProducts(newList)
                        }
                    }
                })
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArray(KEY_PRODUCTS, productAdapter.currentList.toTypedArray())
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
    }
}
