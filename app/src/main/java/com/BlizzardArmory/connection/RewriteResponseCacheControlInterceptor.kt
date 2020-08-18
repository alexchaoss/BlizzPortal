package com.BlizzardArmory.connection

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class RewriteResponseCacheControlInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val maxStale = 60 * 60 * 24 * 5
        val originalResponse: Response = chain.proceed(chain.request())
        return originalResponse.newBuilder()
                .header("Cache-Control", "public, max-age=120, max-stale=$maxStale").build()
    }
}