package com.nikbrik.newsbyviewpager

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.nikbrik.newsbyviewpager.databinding.FragmentViewpagerBinding
import kotlin.math.abs
import kotlin.random.Random

const val KEY_CHECKBOXES = "KEY_CHECKBOXES"
const val KEY_BADGES = "KEY_BADGES"

class ViewPagerFragment : Fragment(R.layout.fragment_viewpager), MultiChoiceDialogListener {
    private val binding: FragmentViewpagerBinding by viewBinding()

    private val articles = listOf(
        Article(
            R.string.sherlok_title,
            R.string.sherlok,
            R.drawable.sherlok,
            listOf(ArticleTag.IT)
        ),
        Article(
            R.string.text_view_title,
            R.string.text_view,
            R.drawable.text_view,
            listOf(ArticleTag.IT)
        ),
        Article(
            R.string.liverpool_title,
            R.string.liverpool,
            R.drawable.liverpool_image,
            listOf(ArticleTag.SPORT)
        ),
        Article(
            R.string.cosmos_title,
            R.string.cosmos,
            R.drawable.cosmos,
            listOf(ArticleTag.GLOBAL)
        ),
    )

    private var checkBoxes = ArticleTag.getBooleanArray()

    override fun onMultiChoiceDialogApply(tagCheckBoxes: BooleanArray?) {
        tagCheckBoxes?.let {
            for (i in 0..it.lastIndex) {
                checkBoxes[i] = it[i]
            }
        }
        updateArticles()
    }

    private fun updateArticles() {
        // фильтр статей
        val listEnabledTags = mutableListOf<ArticleTag>()
        for (i in 0..checkBoxes.lastIndex) {
            if (checkBoxes[i]) listEnabledTags.add(ArticleTag.values()[i])
        }

        val filteredArticles = articles.filter {
            it.tags.any {
                listEnabledTags.contains(it)
            }
        }
        binding.viewPager.adapter = ArticlesAdapter(
            filteredArticles,
            this
        )
        // установка нижнего индикатора
        binding.wormDotsIndicator.setViewPager2(binding.viewPager)
        // Установка вкладок
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = getText(filteredArticles[position].titleId)
        }.attach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateArticles()
        setPageAnimation()

        // badges
        binding.addBadge.setOnClickListener {
            val randomTabNumber = Random.Default.nextInt(until = articles.size)
            setBadge(randomTabNumber, incBadgeNumber = 1)
        }
        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.removeBadge()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        initToolbar()

    }

    private fun setBadge(tabNumber: Int, incBadgeNumber: Int) {
        val tab = binding.tabs.getTabAt(tabNumber)
        val badge = tab?.getOrCreateBadge()
        badge?.apply { number += incBadgeNumber }
    }

    private fun initToolbar() {
        binding.toolbar.apply {
            title = ""

            // Настройка диалога фильтра
            menu.findItem(R.id.filter).setOnMenuItemClickListener {
                FilterDialogFragment()
                    .withArguments {
                        putBooleanArray(
                            FilterDialogFragment.KEY_BARRAY,
                            checkBoxes.copyOf()
                        )
                    }
                    .show(childFragmentManager, "filterDialogFragment")

                true
            }
        }
    }

    private fun setPageAnimation() {
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
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBooleanArray(KEY_CHECKBOXES, checkBoxes)

        // Сохранение бэйджей
        val badgeNumbers = arrayListOf<Int>()
        for (i in 0 until binding.tabs.tabCount) {
            badgeNumbers.add(
                binding.tabs.getTabAt(i)?.badge
                    ?.number ?: 0
            )
        }
        outState.putIntegerArrayList(KEY_BADGES, badgeNumbers)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        // Восстановление фильтрованных вкладок
        savedInstanceState?.getBooleanArray(KEY_CHECKBOXES)?.let { checkBoxes = it }
        updateArticles()

        // Восстановление бэйджей
        val badges = savedInstanceState?.getIntegerArrayList(KEY_BADGES)
        badges?.forEach {
            if (it != 0) setBadge(badges.indexOf(it), it)
        }
    }
}
