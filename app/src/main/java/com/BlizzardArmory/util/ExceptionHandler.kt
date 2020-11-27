package com.BlizzardArmory.util

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.crashlytics.FirebaseCrashlytics

class ExceptionHandler(val context: Context) : Thread.UncaughtExceptionHandler {
    override fun uncaughtException(p0: Thread, p1: Throwable) {
        Toast.makeText(context, "There was an error, some information might not be displayed", Toast.LENGTH_SHORT).show()
        Log.e("Crash Prevented", p1.message!!)
        handleUncaughtException(p0, p1)
    }

    private fun handleUncaughtException(thread: Thread?, e: Throwable) {
        FirebaseCrashlytics.getInstance().log(e.message!!)
    }

}