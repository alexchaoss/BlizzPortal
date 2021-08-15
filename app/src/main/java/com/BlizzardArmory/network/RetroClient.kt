package com.BlizzardArmory.network

import android.content.Context
import com.BlizzardArmory.network.services.*
import com.BlizzardArmory.util.ConnectionStatus
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetroClient {

    private fun getClient(context: Context, logsToggled: Boolean, cacheTime: Long = 7): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val cacheSize = 60 * 1024 * 1024L
        val cache = Cache(context.cacheDir, cacheSize)

        val networkMonitor = object : OkCacheControl.NetworkMonitor {
            override val isOnline: Boolean
                get() = ConnectionStatus.hasNetwork()
        }


        val client = OkCacheControl.on(OkHttpClient.Builder())
            .overrideServerCachePolicy(cacheTime, TimeUnit.DAYS)
            .forceCacheWhenOffline(networkMonitor)
            .apply()

        if (logsToggled || NetworkUtils.logs) {
            client.addInterceptor(interceptor)
        }

        client.cache(cache)

        return client.build()
    }

    private fun proxyClient(context: Context, logsToggled: Boolean, cacheTime: Long = 7): Retrofit {
        val gson = GsonBuilder().create()

        return Retrofit.Builder()
            .baseUrl(NetworkUtils.HEROKU_PROXY_BASE_URL)
            .client(getClient(context, logsToggled, cacheTime))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private fun apiClient(context: Context, logsToggled: Boolean, cacheTime: Long = 7): Retrofit {
        val gson = GsonBuilder().create()

        return Retrofit.Builder()
            .baseUrl(NetworkUtils.HEROKU_API_BASE_URL)
            .client(getClient(context, logsToggled, cacheTime))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun getAPIClient(context: Context, logsToggled: Boolean = false, cacheTime: Long = 7): APINetworkServices {
        return apiClient(context, logsToggled, cacheTime).create(APINetworkServices::class.java)
    }

    fun getGeneralClient(context: Context, logsToggled: Boolean = false, cacheTime: Long = 7): GeneralNetworkServices {
        return proxyClient(context, logsToggled, cacheTime).create(GeneralNetworkServices::class.java)
    }

    fun getWoWClient(context: Context, logsToggled: Boolean = false, cacheTime: Long = 7): WoWNetworkServices {
        return proxyClient(context, logsToggled, cacheTime).create(WoWNetworkServices::class.java)
    }

    fun getD3Client(context: Context, logsToggled: Boolean = false, cacheTime: Long = 7): D3NetworkServices {
        return proxyClient(context, logsToggled, cacheTime).create(D3NetworkServices::class.java)
    }

    fun getSc2Client(context: Context, logsToggled: Boolean = false, cacheTime: Long = 7): Sc2NetworkServices {
        return proxyClient(context, logsToggled, cacheTime).create(Sc2NetworkServices::class.java)
    }

    fun getOWClient(context: Context, logsToggled: Boolean = false, cacheTime: Long = 7): OWNetworkServices {
        return proxyClient(context, logsToggled, cacheTime).create(OWNetworkServices::class.java)
    }
}