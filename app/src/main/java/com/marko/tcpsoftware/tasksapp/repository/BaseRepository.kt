package com.marko.tcpsoftware.tasksapp.repository

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BaseRepository {

    private val BASE_URL = "https://demo9094133.mockable.io/"

    private val okHttp by lazy {
        OkHttpClient.Builder().build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    fun <T> getService(clsOfService: Class<T>): T {
        return retrofit.create(clsOfService)
    }
}