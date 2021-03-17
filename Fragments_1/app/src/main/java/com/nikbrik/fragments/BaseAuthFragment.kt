package com.nikbrik.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment

open class BaseAuthFragment(
    val contentLayoutId: Int,
) : Fragment(contentLayoutId) {

    companion object {
        var isAuthorized = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (isAuthorized.not()) {
//            parentFragmentManager.popBackStack()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LoginFragment())
                .commit()
//            val loginIntent =
//                Intent(this, LoginActivity::class.java)
//            startActivity(loginIntent)
//            finish()
        }
    }

}