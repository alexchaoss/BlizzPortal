package com.BlizzardArmory

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager

/**
 * The type Blizzard armory.
 */
class BlizzardArmory : Application() {
    override fun onCreate() {
        super.onCreate()
        if (instance == null) {
            instance = this
        }
    }

    private val isNetworkConnected: Boolean
        get() {
            val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            return activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting
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