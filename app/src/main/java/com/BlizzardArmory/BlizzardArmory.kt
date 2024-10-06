package com.BlizzardArmory

import android.app.Application
import android.content.Context
import android.util.Log
import com.akexorcist.localizationactivity.core.LocalizationApplicationDelegate
import java.util.*

/**
 * The type Blizzard armory.
 */
class BlizzardArmory : Application() {

    private val localizationDelegate = LocalizationApplicationDelegate()

    override fun attachBaseContext(base: Context) {
        localizationDelegate.setDefaultLanguage(base, Locale.ENGLISH)
        super.attachBaseContext(localizationDelegate.attachBaseContext(base))
    }

    override fun onCreate() {
        super.onCreate()
        if (instance == null) {
            instance = this
        }
        Thread.setDefaultUncaughtExceptionHandler { _, throwable ->
            Log.e("Crash Prevented", throwable.message!!)
        }
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