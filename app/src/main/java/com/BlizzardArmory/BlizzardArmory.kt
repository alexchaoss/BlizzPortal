package com.BlizzardArmory

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.crashlytics.FirebaseCrashlytics


/**
 * The type Blizzard armory.
 */
class BlizzardArmory : Application() {
    override fun onCreate() {
        super.onCreate()
        if (instance == null) {
            instance = this
        }
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            handleUncaughtException(thread, throwable)
        }
    }

    private fun handleUncaughtException(thread: Thread?, e: Throwable) {
        FirebaseCrashlytics.getInstance().log(e.message!!)
    }

    private val isNetworkConnected: Boolean
        @RequiresApi(Build.VERSION_CODES.M)
        get() {
            val result: Boolean
            val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                    connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }

            return result
        }

    companion object {
        /**
         * Gets instance.
         *
         * @return the instance
         */
        var instance: BlizzardArmory? = null
            private set

        /**
         * Has network boolean.
         *
         * @return the boolean
         */
        fun hasNetwork(): Boolean {
            return instance!!.isNetworkConnected
        }
    }
}