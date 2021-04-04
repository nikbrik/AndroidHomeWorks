package com.nikbrik.newsbyviewpager

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Article(
    @StringRes val titleId: Int,
    @StringRes val textId: Int,
    @DrawableRes val imageId: Int,
//    val tags: List<ArticleTag>,
)
