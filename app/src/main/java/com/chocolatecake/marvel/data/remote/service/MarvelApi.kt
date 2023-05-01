package com.chocolatecake.marvel.data.remote.service

import com.chocolatecake.marvel.data.remote.MarvelInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MarvelApi {
    private val client = OkHttpClient.Builder()
        .addInterceptor(MarvelInterceptor())
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()
    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    val apiService: MarvelService = retrofit.create(MarvelService::class.java)

    companion object {
        private const val BASE_URL = "https://gateway.marvel.com/v1/public/"
    }
}