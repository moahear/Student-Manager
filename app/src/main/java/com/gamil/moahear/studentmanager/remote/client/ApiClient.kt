package com.gamil.moahear.studentmanager.remote.client

import com.gamil.moahear.studentmanager.BuildConfig
import com.gamil.moahear.studentmanager.remote.service.IApiService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    val apiService by lazy {
        Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .client(
                OkHttpClient.Builder().connectTimeout(60L, TimeUnit.SECONDS)
                    .writeTimeout(60L, TimeUnit.SECONDS).readTimeout(60L, TimeUnit.SECONDS)
                    .readTimeout(60L, TimeUnit.SECONDS)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build().create(IApiService::class.java)
    }
}