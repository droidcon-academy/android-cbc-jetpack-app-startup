package com.droidcon.accelappstartup.startup.initializer

import android.content.Context
import androidx.startup.Initializer
import com.droidcon.accelappstartup.startup.dependency.FakeLogger

class LoggerInitializer: Initializer<FakeLogger> {
    override fun create(context: Context): FakeLogger {
        return FakeLogger()
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}