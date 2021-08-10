package com.nikbrik.moshiHW.app

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.soloader.SoLoader
import com.nikbrik.moshiHW.BuildConfig.DEBUG
import com.nikbrik.moshiHW.network.Networking
import timber.log.Timber

class NetworkingApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // loging
        if (DEBUG && Timber.treeCount() == 0) Timber.plant(Timber.DebugTree())

        // flipper
        SoLoader.init(this, false)

        if (DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            AndroidFlipperClient.getInstance(this)
                .apply {
                    addPlugin(
                        InspectorFlipperPlugin(
                            this@NetworkingApplication,
                            DescriptorMapping.withDefaults()
                        )
                    )
                    addPlugin(Networking.flipperNetworkPlugin)
                }
                .start()
        }
    }
}
