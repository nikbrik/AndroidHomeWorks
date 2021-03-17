package com.nikbrik.intents

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nikbrik.intents.databinding.ActivitySecondaryBinding

class SecondaryActivity : BaseAuthActivity() {

    private val binding: ActivitySecondaryBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)
        if (isFinishing.not()) {
            binding.callButton.setOnClickListener {
                val phoneNumber = binding.phoneText.text.toString()
                if (Patterns.PHONE.matcher(phoneNumber).matches()) {
                    val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                        data = Uri.parse("tel: $phoneNumber")
                    }
                    if (dialIntent.resolveActivity(packageManager) != null) {
                        startActivity(dialIntent)
                    } else {
                        toast("No app found for calls")
                    }
                } else {
                    toast(getString(R.string.invalid_phone_number))
                }
            }
        }
    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
