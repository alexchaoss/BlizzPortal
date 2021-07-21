package com.BlizzardArmory.util

import android.app.NativeActivity
import android.content.Context
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.crashlytics.FirebaseCrashlytics

class ExceptionHandler(val context: Context) : Thread.UncaughtExceptionHandler {
    override fun uncaughtException(p0: Thread, p1: Throwable) {
        Snackbar.make((context as NativeActivity).findViewById(android.R.id.content), "There was an error, some information might not be displayed", Snackbar.LENGTH_SHORT)
            .show()
        Log.e("Crash Prevented", p1.message!!)
        handleUncaughtException(p0, p1)
    }

    private fun handleUncaughtException(thread: Thread?, e: Throwable) {
        FirebaseCrashlytics.getInstance().log(e.message!!)
    }

}