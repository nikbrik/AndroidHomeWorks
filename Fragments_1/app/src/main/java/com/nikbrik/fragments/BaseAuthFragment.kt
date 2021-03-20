package com.nikbrik.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment

open class BaseAuthFragment(
    private val contentLayoutId: Int,
) : Fragment(contentLayoutId) {

    companion object {
        var isAuthorized = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (isAuthorized.not()) {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_activity_container, LoginFragment())
                .commit()
        }
    }
}
