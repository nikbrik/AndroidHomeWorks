package com.nikbrik.newsbyviewpager

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.nikbrik.newsbyviewpager.databinding.FragmentViewpagerBinding
import kotlin.math.abs

class ViewPagerFragment : Fragment(R.layout.fragment_viewpager) {
    private val binding: FragmentViewpagerBinding by viewBinding()

    private val articles = listOf(
        Article(
            R.string.sherlok_title,
            R.string.sherlok,
            R.drawable.sherlok,
        ),
        Article(
            R.string.text_view_title,
            R.string.text_view,
            R.drawable.text_view,
        )
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager.adapter = ArticlesAdapter(articles, this)
        binding.wormDotsIndicator.setViewPager2(binding.viewPager)
        binding.viewPager.setPageTransformer { page, position ->
            page.apply {

                translationX = -position * width
                pivotX = 0f
                pivotY = height / 2f
                cameraDistance = 20000f

                when {
                    // [-Infinity,-1)
                    position < -1 -> alpha = 0f // This page is way off-screen to the left.
                    // [-1,0]
                    position <= 0 -> {
                        alpha = 1f
                        rotationY = -120 * abs(position)
                    }
                    // (0,1]
                    position <= 1 -> {
                        alpha = 1f
                        rotationY = 120 * abs(position)
                    }
                    // (1,+Infinity]
                    else -> alpha = 0f // This page is way off-screen to the right.
                }
            }
        }
        TabLayoutMediator(binding.tabs, binding.viewPager) { _, _ ->
        }.attach()
    }
}
