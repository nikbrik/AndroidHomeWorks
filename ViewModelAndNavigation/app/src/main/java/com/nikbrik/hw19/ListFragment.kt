package com.nikbrik.hw19

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nikbrik.hw19.adapters.EndlessRecyclerViewScrollListener
import com.nikbrik.hw19.adapters.ProductAdapter
import com.nikbrik.hw19.databinding.FragmentListBinding
import jp.wasabeef.recyclerview.animators.ScaleInAnimator

const val TYPE_LINEAR = 1
const val TYPE_GRID = 2
const val TYPE_STAGGERED_GRID = 3

class ListFragment : Fragment(R.layout.fragment_list) {

    private val binding: FragmentListBinding by viewBinding()
    private var productAdapter: ProductAdapter by autoCleared()
    private var layoutManagerType = TYPE_LINEAR
    private val productsViewModel: ProductsViewModel by activityViewModels()
    private val args: ListFragmentArgs by navArgs()
    private var needToScrollUp = false

    private fun removeClickedItem(position: Int) {
        productsViewModel.removeProduct(position)
        updateRecyclerViewPlaceholder()
    }

    private fun updateRecyclerViewPlaceholder() {
        productAdapter.apply {
            binding.recyclerViewPlaceholder.isVisible = isEmpty
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layoutManagerType = args.type
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addElement.setOnClickListener {
            needToScrollUp = true
            findNavController().navigate(
                ListFragmentDirections.actionListFragmentToNewItemDialogFragment()
            )
        }
        initList()
        observeViewModelState()
    }

    private fun observeViewModelState() {
        productsViewModel.products.observe(viewLifecycleOwner) { newList ->
            productAdapter.items = newList
            if (needToScrollUp) {
                needToScrollUp = false
                binding.recyclerView.smoothScrollToPosition(0)
            }

            updateRecyclerViewPlaceholder()
        }
        productsViewModel.showToast.observe(viewLifecycleOwner) { position ->
            Toast.makeText(
                requireContext(),
                "Delete item at position $position",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun initList() {
        productAdapter =
            ProductAdapter(
                layoutManagerType,
                { position ->
                    findNavController().navigate(
                        ListFragmentDirections.actionListFragmentToDetailFragment(position)
                    )
                },
                { position ->
                    removeClickedItem(position)
                },
            )
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

            addItemDecoration(CustomItemDecoration())
            itemAnimator = ScaleInAnimator()

            setHasFixedSize(true)

            layoutManager?.let {
                addOnScrollListener(
                    object : EndlessRecyclerViewScrollListener(it) {
                        override fun onLoadMore(
                            page: Int,
                            totalItemsCount: Int,
                            view: RecyclerView?
                        ) {
                            productsViewModel.loadMore(totalItemsCount)
                        }
                    }
                )
            }
        }
    }
}
