package com.nikbrik.newsbyviewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ArticlesAdapter(
    private val articles: List<Article>,
    fragment: ViewPagerFragment,
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return articles.size
    }

    override fun createFragment(position: Int): Fragment {
        return ArticleFragment.newInstance(
            articles[position].titleId,
            articles[position].textId,
            articles[position].imageId,
        )
    }
}
