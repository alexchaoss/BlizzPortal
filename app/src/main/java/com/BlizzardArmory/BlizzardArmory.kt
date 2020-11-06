package com.BlizzardArmory

import android.app.Application
import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import kotlin.properties.Delegates

object Variables {
    var isWiFiNetworkConnected: Boolean by Delegates.observable(false) { property, oldValue, newValue ->
        Log.i("Wi-Fi connectivity", "$newValue")
    }

    var isDataNetworkConnected: Boolean by Delegates.observable(false) { property, oldValue, newValue ->
        Log.i("Data connectivity", "$newValue")
    }
}

/**
 * The type Blizzard armory.
 */
class BlizzardArmory : Application() {
    override fun onCreate() {
        super.onCreate()
        if (instance == null) {
            instance = this
        }
        Thread.setDefaultUncaughtExceptionHandler { _, throwable ->
            Log.e("Crash Prevented", throwable.message!!)
            handleUncaughtException(throwable)
        }
    }

    private fun handleUncaughtException(e: Throwable) {
        FirebaseCrashlytics.getInstance().log(e.message!!)
    }

    companion object {
        /**
         * Gets instance.
         *
         * @return the instance
         */
        var instance: BlizzardArmory? = null
            private set
    }
}