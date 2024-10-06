package com.BlizzardArmory.util

import android.app.NativeActivity
import android.content.Context
import android.util.Log
import com.google.android.material.snackbar.Snackbar

class ExceptionHandler(val context: Context) : Thread.UncaughtExceptionHandler {
    override fun uncaughtException(p0: Thread, p1: Throwable) {
        Snackbar.make((context as NativeActivity).findViewById(android.R.id.content), "There was an error, some information might not be displayed", Snackbar.LENGTH_SHORT)
            .show()

        handleUncaughtException(p1)
    }

    private fun handleUncaughtException(e: Throwable) {
        Log.e("Crash Prevented", e.message!!)
    }

}