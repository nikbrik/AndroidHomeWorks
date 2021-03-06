package com.nikbrik.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nikbrik.fragments.databinding.FragmentCallBinding

class CallFragment : BaseAuthFragment(R.layout.fragment_call) {
    private val binding: FragmentCallBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.callButton.setOnClickListener {
            val phoneNumber = binding.phoneText.text.toString()
            if (Patterns.PHONE.matcher(phoneNumber).matches()) {
                val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel: $phoneNumber")
                }
                activity?.let {
                    if (dialIntent.resolveActivity(it.packageManager) != null) {
                        startActivity(dialIntent)
                    } else {
                        toast("No app found for calls")
                    }
                }
            } else {
                toast(getString(R.string.invalid_phone_number))
            }
        }
    }
    private fun toast(text: String) {
        Toast.makeText(this.context, text, Toast.LENGTH_SHORT).show()
    }
}
