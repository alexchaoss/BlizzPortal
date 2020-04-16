package com.BlizzardArmory.connection

import com.BlizzardArmory.BlizzardArmory
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroClient {
    var BASE_URL: String = URLConstants.HEROKU_AUTHENTICATE
    val getClient: NetworkServices
        get() {

            val cacheSize: Long = 30 * 1024 * 1024 // 10 MB
            val cache = Cache(BlizzardArmory.getInstance().cacheDir, cacheSize)

            val gson = GsonBuilder().create()

            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient
                    .Builder()
                    .cache(cache)
                    //.addInterceptor(interceptor)
                    .build()

            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

            return retrofit.create(NetworkServices::class.java)

        }
}