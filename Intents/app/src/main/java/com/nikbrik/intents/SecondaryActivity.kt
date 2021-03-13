package com.nikbrik.intents

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.nikbrik.intents.databinding.ActivitySecondaryBinding

class SecondaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondaryBinding
    private val dial =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            result?.run {
                binding.resultText.text =
                    "Result code: ${resultCode}" + System.lineSeparator() + "Result data: ${data}"
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.callButton.setOnClickListener {
            val phoneNumber = binding.phoneText.text.toString();
            if (Patterns.PHONE.matcher(phoneNumber).matches()) {
                val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel: $phoneNumber")
                }
                if (dialIntent.resolveActivity(packageManager) != null) {
                    dial.launch(dialIntent)
                } else {
                    toast("No app found for calls")
                }
            } else {
                toast(getString(R.string.invalid_phone_number))
            }
        }
    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
