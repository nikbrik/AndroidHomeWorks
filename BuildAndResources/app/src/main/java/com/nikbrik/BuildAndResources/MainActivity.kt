package com.nikbrik.BuildAndResources

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        """flavor = ${BuildConfig.FLAVOR}
        |buildType = ${BuildConfig.BUILD_TYPE}
        |versionCode = ${BuildConfig.VERSION_CODE}
        |versionName = ${BuildConfig.VERSION_NAME}
        |applicationId = ${BuildConfig.APPLICATION_ID}
        |""".trimMargin()
        .also { findViewById<TextView>(R.id.mainText).text = it }
    }
}