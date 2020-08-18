package com.BlizzardArmory.connection

import com.BlizzardArmory.BlizzardArmory
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class RewriteRequestInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val maxStale = 60 * 60 * 24 * 5
        val request: Request
        request = if (BlizzardArmory.hasNetwork()) {
            chain.request()
        } else {
            chain.request().newBuilder().header("Cache-Control", "max-stale=$maxStale").build()
        }
        return chain.proceed(request)
    }
}