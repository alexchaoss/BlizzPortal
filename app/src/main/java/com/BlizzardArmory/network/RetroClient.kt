package com.BlizzardArmory.network

import android.content.Context
import com.BlizzardArmory.network.services.*
import com.BlizzardArmory.util.ConnectionStatus
import com.google.gson.GsonBuilder
import com.ncornette.cache.OkCacheControl
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetroClient {

    private fun getClient(context: Context): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val cacheSize = 60 * 1024 * 1024L
        val cache = Cache(context.cacheDir, cacheSize)

        val networkMonitor = OkCacheControl.NetworkMonitor {
            ConnectionStatus.hasNetwork()
        }

        val client = OkCacheControl.on(OkHttpClient.Builder())
            .overrideServerCachePolicy(1, TimeUnit.MINUTES)
            .forceCacheWhenOffline(networkMonitor)
            .apply()
            .addInterceptor(interceptor)
            .cache(cache)
            .build();

        return client
    }

    private fun proxyClient(context: Context): Retrofit {
        val gson = GsonBuilder().create()

        return Retrofit.Builder()
            .baseUrl(NetworkUtils.HEROKU_PROXY_BASE_URL)
            .client(getClient(context))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private fun apiClient(context: Context): Retrofit {
        val gson = GsonBuilder().create()

        return Retrofit.Builder()
            .baseUrl(NetworkUtils.HEROKU_API_BASE_URL)
            .client(getClient(context))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun getAPIClient(context: Context): APINetworkServices {
        return apiClient(context).create(APINetworkServices::class.java)
    }

    fun getGeneralClient(context: Context): GeneralNetworkServices {
        return proxyClient(context).create(GeneralNetworkServices::class.java)
    }

    fun getWoWClient(context: Context): WoWNetworkServices {
        return proxyClient(context).create(WoWNetworkServices::class.java)
    }

    fun getD3Client(context: Context): D3NetworkServices {
        return proxyClient(context).create(D3NetworkServices::class.java)
    }

    fun getSc2Client(context: Context): Sc2NetworkServices {
        return proxyClient(context).create(Sc2NetworkServices::class.java)
    }

    fun getOWClient(context: Context): OWNetworkServices {
        return proxyClient(context).create(OWNetworkServices::class.java)
    }
}