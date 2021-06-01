package com.nikbrik.hw19

import android.os.Bundle
import androidx.fragment.app.Fragment

fun <T : Fragment> T.withArguments(action: Bundle.() -> Unit): T {
    return apply {
        arguments = Bundle().apply(action)
    }
}
