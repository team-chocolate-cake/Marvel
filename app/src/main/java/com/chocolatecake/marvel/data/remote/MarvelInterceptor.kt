package com.chocolatecake.marvel.data.remote

import com.chocolatecake.marvel.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
class MarvelInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val hashed = HashGenerator.generateHash(BuildConfig.PRIVATE_KEY, BuildConfig.PUBLIC_KEY)

        val originalRequest = chain.request()
        val url = originalRequest.url.newBuilder()
            .addQueryParameter("ts", HashGenerator.timestamp)
            .addQueryParameter("apikey", BuildConfig.PUBLIC_KEY)
            .addQueryParameter("hash", hashed)
            .build()

        val requestBuilder = originalRequest.newBuilder().url(url)

        return chain.proceed(requestBuilder.build())
    }
}