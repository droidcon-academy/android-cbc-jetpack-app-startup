package com.droidcon.accelappstartup.startup.initializer

import android.content.Context
import androidx.startup.Initializer
import com.droidcon.accelappstartup.startup.dependency.FakeAdsService

class AdServiceInitializer: Initializer<FakeAdsService> {

    override fun create(context: Context): FakeAdsService {
        return FakeAdsService()
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}