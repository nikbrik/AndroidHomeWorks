package com.nikbrik.lists

import android.content.Context
import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CustomItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val offset = 10.dpToPx(view.context)
        outRect.apply {
            left = offset
            right = offset
            top = offset
            bottom = offset
        }
    }

    fun Int.dpToPx(context: Context): Int {
        return this * context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT
    }
}