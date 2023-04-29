package com.chocolatecake.marvel.data.remote.service

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MarvelRequest {

    private val BASE_URL="https://gateway.marvel.com/v1/public/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    val apiService: MarvelService = retrofit.create(MarvelService::class.java)
}