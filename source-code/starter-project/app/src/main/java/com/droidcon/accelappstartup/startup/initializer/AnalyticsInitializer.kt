package com.droidcon.accelappstartup.startup.initializer

import android.content.Context
import androidx.startup.Initializer
import com.droidcon.accelappstartup.startup.dependency.FakeAnalyticsLibrary

class AnalyticsInitializer: Initializer<FakeAnalyticsLibrary> {

    override fun create(context: Context): FakeAnalyticsLibrary {
        return FakeAnalyticsLibrary()
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}