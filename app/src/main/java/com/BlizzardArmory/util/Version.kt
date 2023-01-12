package com.BlizzardArmory.util

import android.content.Context
import com.BlizzardArmory.BuildConfig

object Version {
    fun code(): Int {
        return BuildConfig.VERSION_CODE
    }

    fun name(): String {
        return BuildConfig.VERSION_NAME
    }
}