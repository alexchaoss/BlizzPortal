package com.BlizzardArmory

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
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
        Thread.setDefaultUncaughtExceptionHandler { _, throwable ->
            Log.e("Crash Prevented", throwable.message!!)
            handleUncaughtException(throwable)
        }
    }

    private fun handleUncaughtException(e: Throwable) {
        FirebaseCrashlytics.getInstance().log(e.message!!)
    }

    private fun getConnectionType(context: Context): Int {
        var result = 0
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                            result = 2
                        }
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                            result = 1
                        }
                        hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> {
                            result = 3
                        }
                    }
                }
            }
        } else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    when (type) {
                        ConnectivityManager.TYPE_WIFI -> {
                            result = 2
                        }
                        ConnectivityManager.TYPE_MOBILE -> {
                            result = 1
                        }
                        ConnectivityManager.TYPE_VPN -> {
                            result = 3
                        }
                    }
                }
            }
        }
        return result
    }

    private val isNetworkConnected: Boolean
        get() {
            return getConnectionType(this) != 0
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