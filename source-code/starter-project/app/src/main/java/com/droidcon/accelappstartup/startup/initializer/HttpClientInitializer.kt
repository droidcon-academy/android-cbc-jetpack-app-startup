package com.droidcon.accelappstartup.startup.initializer

import android.content.Context
import androidx.startup.Initializer
import com.droidcon.accelappstartup.startup.dependency.FakeHttpClient

class HttpClientInitializer: Initializer<FakeHttpClient> {
    override fun create(context: Context): FakeHttpClient {
        return FakeHttpClient()
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}