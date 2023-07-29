package com.droidcon.accelappstartup.startup.initializer

import android.content.Context
import androidx.startup.Initializer
import com.droidcon.accelappstartup.startup.dependency.FakeAuthenticationService

class AuthServiceInitializer: Initializer<FakeAuthenticationService> {
    override fun create(context: Context): FakeAuthenticationService {
        return FakeAuthenticationService()
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }

}