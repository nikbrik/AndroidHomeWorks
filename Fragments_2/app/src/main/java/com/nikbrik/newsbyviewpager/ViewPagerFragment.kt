package com.nikbrik.newsbyviewpager

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nikbrik.newsbyviewpager.databinding.FragmentViewpagerBinding

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
    }
}
