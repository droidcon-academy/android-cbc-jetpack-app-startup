package com.droidcon.accelappstartup

import android.app.Application
import com.droidcon.accelappstartup.startup.dependency.AdsManager
import com.droidcon.accelappstartup.startup.dependency.AuthenticationRepository
import com.droidcon.accelappstartup.startup.dependency.FakeAdsService
import com.droidcon.accelappstartup.startup.dependency.FakeAnalyticsLibrary
import com.droidcon.accelappstartup.startup.dependency.FakeAuthenticationService
import com.droidcon.accelappstartup.startup.dependency.FakeDatabase
import com.droidcon.accelappstartup.startup.dependency.FakeFirebaseAppConfigManager
import com.droidcon.accelappstartup.startup.dependency.FakeHttpClient
import com.droidcon.accelappstartup.startup.dependency.FakeLogger
import com.droidcon.accelappstartup.tools.PerfTracer

class CustomApplication : Application() {

    private lateinit var adsManager: AdsManager
    private lateinit var analyticsLibrary: FakeAnalyticsLibrary
    private lateinit var authenticationRepository: AuthenticationRepository
    private lateinit var adService: FakeAdsService
    private lateinit var authService: FakeAuthenticationService
    private lateinit var database: FakeDatabase
    private lateinit var httpClient: FakeHttpClient
    private lateinit var logger: FakeLogger
    private lateinit var firebaseAppConfigManager: FakeFirebaseAppConfigManager

    override fun onCreate() {
        super.onCreate()
        initStartup()
    }

    private fun initStartup() {
        // Without App Startup Lib
        startupDuration = PerfTracer.measureMs {
            adService = FakeAdsService()
            authService = FakeAuthenticationService()
            analyticsLibrary = FakeAnalyticsLibrary()
            database = FakeDatabase()
            httpClient = FakeHttpClient()
            logger = FakeLogger()
            firebaseAppConfigManager = FakeFirebaseAppConfigManager()

            authenticationRepository = AuthenticationRepository(authService)
            adsManager = AdsManager(adService)
        }
    }

    companion object {
        var startupDuration = 0L
    }
}