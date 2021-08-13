package com.BlizzardArmory.util


import android.util.Log
import kotlin.properties.Delegates

class ConnectionStatus {
    companion object {
        var isWiFiNetworkConnected: Boolean by Delegates.observable(false) { _, _, newValue ->
            Log.i("Wi-Fi connectivity", "$newValue")
        }


        var isDataNetworkConnected: Boolean by Delegates.observable(false) { _, _, newValue ->
            Log.i("Data connectivity", "$newValue")
        }

        fun hasNetwork(): Boolean {
            return isWiFiNetworkConnected || isDataNetworkConnected
        }
    }
}