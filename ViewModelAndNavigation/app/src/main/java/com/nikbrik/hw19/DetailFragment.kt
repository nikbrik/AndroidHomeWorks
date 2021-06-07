package com.nikbrik.hw19

import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.nikbrik.hw19.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val binding: FragmentDetailBinding by viewBinding()
    private val args: DetailFragmentArgs by navArgs()
    private val productsViewModel: ProductsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productsViewModel.products.value?.let {
            when (val product: Product = it[args.position]) {
                is Product.Fruit -> bindViewWithProduct(
                    product.photoLink,
                    product.title,
                    product.description,
                    R.drawable.fruits_placeholder,
                )
                is Product.Vegetable -> bindViewWithProduct(
                    product.photoLink,
                    product.title,
                    product.description,
                    R.drawable.vegetables_placeholder,
                )
            }
        } ?: error("Products list is empty but clicked")
    }

    private fun bindViewWithProduct(
        imageLink: String,
        titleString: String,
        descriptionString: String,
        @DrawableRes placeHolderId: Int,
    ) {
        binding.run {
            title.text = titleString
            description.text = descriptionString
            Glide.with(root)
                .load(if (imageLink.isBlank()) placeHolderId else imageLink)
                .placeholder(placeHolderId)
                .error(R.drawable.no_image_available)
                .into(photo)
        }
    }
}
