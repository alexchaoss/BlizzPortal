package com.BlizzardArmory.network

import com.BlizzardArmory.BlizzardArmory
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
    var BASE_URL: String = URLConstants.HEROKU_AUTHENTICATE

    private fun baseClient(): Retrofit {
        val gson = GsonBuilder().create()

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        val client = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .cache(
                Cache(
                    directory = BlizzardArmory.instance?.cacheDir!!,
                    maxSize = 60 * 1024 * 1024
                )
            )
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (ConnectionStatus.hasNetwork()) {
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 60).build()
                } else {
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                    ).build()
                }
                chain.proceed(request)
            }
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun getGeneralClient(): GeneralNetworkServices {
        return baseClient().create(GeneralNetworkServices::class.java)
    }

    fun getWoWClient(): WoWNetworkServices {
        return baseClient().create(WoWNetworkServices::class.java)
    }

    fun getD3Client(): D3NetworkServices {
        return baseClient().create(D3NetworkServices::class.java)
    }

    fun getSc2Client(): Sc2NetworkServices {
        return baseClient().create(Sc2NetworkServices::class.java)
    }

    fun getOWClient(): OWNetworkServices {
        return baseClient().create(OWNetworkServices::class.java)
    }
}