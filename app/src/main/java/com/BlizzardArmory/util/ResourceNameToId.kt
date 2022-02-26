package com.BlizzardArmory.util

import android.content.Context

object ResourceNameToId {
    fun getDrawableIdFromString(name: String, context: Context): Int {
        return context.resources.getIdentifier(name, "drawable", context.packageName)
    }

    fun getStringIdFromString(name: String, context: Context): Int {
        return context.resources.getIdentifier(name, "string", context.packageName)
    }
}