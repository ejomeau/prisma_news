package com.ejomeau.prismamedia.retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    private var okHttpClient: OkHttpClient? = null

   // val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
   val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
    private var connectTimeout: Long = 300
    private var readTimeout: Long = 300

    private val client: OkHttpClient
        get() {
            if (okHttpClient == null) {
                val httpBuilder = OkHttpClient.Builder()
                httpBuilder
                    .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                    .readTimeout(readTimeout, TimeUnit.SECONDS)
                okHttpClient = httpBuilder.build()
            }
            return okHttpClient!!
        }

    fun <T> rxMakeRequest(baseUrl: String, service: Class<T>): T {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
            .create(service)
    }
}