package com.nikbrik.newsbyviewpager

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.nikbrik.newsbyviewpager.databinding.FragmentArticleBinding

class ArticleFragment : Fragment(R.layout.fragment_article) {

    private val binding: FragmentArticleBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(binding.root)
            .load(requireArguments().getInt(KEY_IMAGE))
            .into(binding.image)

        binding.content.movementMethod = LinkMovementMethod.getInstance()
        binding.content.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(
                getString(requireArguments().getInt(KEY_TEXT)),
                Html.FROM_HTML_MODE_COMPACT
            )
        } else {
            Html.fromHtml(getString(requireArguments().getInt(KEY_TEXT)))
        }
    }

    companion object {
        private const val KEY_TITLE = "ArticleTitle"
        private const val KEY_TEXT = "ArticleText"
        private const val KEY_IMAGE = "ArticleImage"

        fun newInstance(
            @StringRes titleId: Int,
            @StringRes textId: Int,
            @DrawableRes imageId: Int
        ): ArticleFragment {
            return ArticleFragment().withArguments {
                putInt(KEY_TITLE, titleId)
                putInt(KEY_TEXT, textId)
                putInt(KEY_IMAGE, imageId)
            }
        }
    }
}
