package com.droidcon.accelappstartup.startup.initializer

import android.content.Context
import androidx.startup.Initializer
import com.droidcon.accelappstartup.startup.dependency.FakeFirebaseAppConfigManager

class FirebaseAppConfigInitializer: Initializer<FakeFirebaseAppConfigManager> {
    override fun create(context: Context): FakeFirebaseAppConfigManager {
        return FakeFirebaseAppConfigManager()
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}