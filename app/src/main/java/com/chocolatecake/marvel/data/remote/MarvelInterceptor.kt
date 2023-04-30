package com.chocolatecake.marvel.data.remote

import com.chocolatecake.marvel.BuildConfig
import com.chocolatecake.marvel.data.util.GenerateHash
import okhttp3.Interceptor
import okhttp3.Response


class MarvelInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val timestamp = System.currentTimeMillis().toString()
        val hash = GenerateHash().generate(timestamp)

        val originalRequest = chain.request()
        val url = originalRequest.url.newBuilder()
            .addQueryParameter("ts", timestamp)
            .addQueryParameter("apikey", BuildConfig.PUBLIC_KEY)
            .addQueryParameter("hash", hash)
            .build()

        val requestBuilder = originalRequest.newBuilder()
            .url(url)

        return chain.proceed(requestBuilder.build())
    }
}