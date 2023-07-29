package com.droidcon.accelappstartup.startup.initializer

import android.content.Context
import androidx.startup.Initializer
import com.droidcon.accelappstartup.startup.FakeDatabase

class DatabaseInitializer: Initializer<FakeDatabase> {
    override fun create(context: Context): FakeDatabase {
        return FakeDatabase()
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}