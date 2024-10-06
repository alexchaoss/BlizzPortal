package com.BlizzardArmory.util

import android.content.Context
import com.BlizzardArmory.BlizzardArmory


object Version {
    fun code(): Int {
        return BlizzardArmory.instance.let { instance ->
            instance?.packageName?.let {
                instance.applicationContext?.packageManager?.getPackageInfo(it, 0)?.versionCode
            }
        } ?: 0
    }

    fun name(): String {
        return BlizzardArmory.instance.let { instance ->
            instance?.packageName?.let {
                instance.applicationContext?.packageManager?.getPackageInfo(it, 0)?.versionName
            }
        } ?: ""
    }
}