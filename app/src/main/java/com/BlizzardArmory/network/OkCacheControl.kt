package com.BlizzardArmory.network

import okhttp3.*
import java.util.concurrent.TimeUnit


/**
 * Created by nic on 21/06/16.
 */
class OkCacheControl private constructor(okBuilder: OkHttpClient.Builder) {
    /**
     * Created by nic on 20/06/16.
     */
    interface NetworkMonitor {
        val isOnline: Boolean
    }

    interface MaxAgeControl {
        /**
         * @return max-age in seconds
         */
        val maxAge: Long
    }

    private var networkMonitor: NetworkMonitor? = null
    private var maxAgeValue: Long = 0
    private var maxAgeUnit: TimeUnit? = null
    private val okBuilder: OkHttpClient.Builder
    private var maxAgeControl: MaxAgeControl? = null
    fun overrideServerCachePolicy(maxAgeSeconds: Long?): OkCacheControl {
        return if (maxAgeSeconds == null) {
            this.overrideServerCachePolicy(0, null)
        } else {
            this.overrideServerCachePolicy(maxAgeSeconds, TimeUnit.SECONDS)
        }
    }

    fun overrideServerCachePolicy(timeValue: Long, unit: TimeUnit?): OkCacheControl {
        maxAgeControl = null
        maxAgeValue = timeValue
        maxAgeUnit = unit
        return this
    }

    fun overrideServerCachePolicy(maxAgeControl: MaxAgeControl?): OkCacheControl {
        maxAgeUnit = null
        this.maxAgeControl = maxAgeControl
        return this
    }

    fun forceCacheWhenOffline(networkMonitor: NetworkMonitor): OkCacheControl {
        this.networkMonitor = networkMonitor
        return this
    }

    fun apply(): OkHttpClient.Builder {
        if (networkMonitor == null && maxAgeUnit == null && maxAgeControl == null) {
            return okBuilder
        }
        if (maxAgeUnit != null) {
            maxAgeControl = StaticMaxAgeControl(maxAgeValue, maxAgeUnit!!)
        }
        val responseHandler: ResponseHandler
        if (maxAgeControl != null) {
            responseHandler = CachePolicyResponseHandler(maxAgeControl!!)
        } else {
            responseHandler = ResponseHandler()
        }
        val requestHandler: RequestHandler
        if (networkMonitor != null) {
            requestHandler = NetworkMonitorRequestHandler(networkMonitor!!)
        } else {
            requestHandler = RequestHandler()
        }
        val cacheControlInterceptor = getOkCacheControlInterceptor(
            requestHandler, responseHandler)
        okBuilder.addNetworkInterceptor(cacheControlInterceptor)
        if (networkMonitor != null) {
            okBuilder.addInterceptor(cacheControlInterceptor)
        }
        return okBuilder
    }

    private class StaticMaxAgeControl(private val maxAgeValue: Long, private val maxAgeUnit: TimeUnit) :
        MaxAgeControl {
        override val maxAge: Long
            get() = maxAgeUnit.toSeconds(maxAgeValue)

    }

    private class CachePolicyResponseHandler(private val maxAgeControl: MaxAgeControl) :
        ResponseHandler() {
        override fun newResponse(response: Response): Response {
            return response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .header("Cache-Control", "max-age=" + maxAgeControl.maxAge)
                .build()
        }
    }

    private class NetworkMonitorRequestHandler(private val networkMonitor: NetworkMonitor) :
        RequestHandler() {
        override fun newRequest(request: Request): Request {
            val newBuilder: Request.Builder = request.newBuilder()
            if (!networkMonitor.isOnline) {
                // To be used with Application Interceptor to use Expired cache
                newBuilder.cacheControl(CacheControl.FORCE_CACHE)
            }
            return newBuilder.build()
        }
    }

    private open class ResponseHandler {
        open fun newResponse(response: Response): Response {
            return response
        }
    }

    private open class RequestHandler {
        open fun newRequest(request: Request): Request {
            return request
        }
    }

    companion object {
        fun on(okBuilder: OkHttpClient.Builder): OkCacheControl {
            return OkCacheControl(okBuilder)
        }

        private fun getOkCacheControlInterceptor(requestHandler: RequestHandler,
                                                 responseHandler: ResponseHandler): Interceptor {
            return Interceptor { chain ->
                val originalRequest = chain.request()
                val request = requestHandler.newRequest(originalRequest)
                val originalResponse = chain.proceed(request)
                responseHandler.newResponse(originalResponse)
            }
        }
    }

    init {
        this.okBuilder = okBuilder
    }
}