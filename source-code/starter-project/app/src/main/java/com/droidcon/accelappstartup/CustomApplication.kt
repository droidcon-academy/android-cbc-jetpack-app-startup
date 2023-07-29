package com.droidcon.accelappstartup

import android.app.Application
import com.droidcon.accelappstartup.startup.AdsManager
import com.droidcon.accelappstartup.startup.AuthenticationRepository
import com.droidcon.accelappstartup.startup.FakeAdsService
import com.droidcon.accelappstartup.startup.FakeAnalyticsLibrary
import com.droidcon.accelappstartup.startup.FakeAuthenticationService
import com.droidcon.accelappstartup.startup.FakeDatabase
import com.droidcon.accelappstartup.startup.FakeFirebaseAppConfigManager
import com.droidcon.accelappstartup.startup.FakeHttpClient
import com.droidcon.accelappstartup.startup.FakeLogger
import com.droidcon.accelappstartup.startup.ParallelInitializer
import com.droidcon.accelappstartup.startup.initializer.AdServiceInitializer
import com.droidcon.accelappstartup.startup.initializer.AnalyticsInitializer
import com.droidcon.accelappstartup.startup.initializer.AuthServiceInitializer
import com.droidcon.accelappstartup.startup.initializer.DatabaseInitializer
import com.droidcon.accelappstartup.startup.initializer.FirebaseAppConfigInitializer
import com.droidcon.accelappstartup.startup.initializer.HttpClientInitializer
import com.droidcon.accelappstartup.startup.initializer.LoggerInitializer
import com.droidcon.accelappstartup.startup.parallelInit
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
        slowStartup()
//        fastStartup()
    }

    private fun fastStartup() {
        startupDuration = PerfTracer.measureMs {
            val zeroDependencyInitializers =
                listOf(ParallelInitializer(AdServiceInitializer()) { adService = it },
                    ParallelInitializer(AuthServiceInitializer()) { authService = it },
                    ParallelInitializer(AnalyticsInitializer()) { analyticsLibrary = it },
                    ParallelInitializer(DatabaseInitializer()) { database = it },
                    ParallelInitializer(FirebaseAppConfigInitializer()) {
                        firebaseAppConfigManager = it
                    },
                    ParallelInitializer(HttpClientInitializer()) { httpClient = it },
                    ParallelInitializer(LoggerInitializer()) { logger = it })
            zeroDependencyInitializers.parallelInit(this)

            authenticationRepository = AuthenticationRepository(authService)
            adsManager = AdsManager(adService)
        }
    }

    private fun slowStartup() {
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