package com.nikbrik.lists

import android.os.Bundle
import androidx.fragment.app.Fragment

fun <T : Fragment> T.withArgument(action: Bundle.() -> Unit): T {
    return apply {
        arguments = Bundle().apply(action)
    }
}