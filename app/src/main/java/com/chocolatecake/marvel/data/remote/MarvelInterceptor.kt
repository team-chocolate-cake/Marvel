package com.chocolatecake.marvel.data.remote

import com.chocolatecake.marvel.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.security.MessageDigest


class MarvelInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val timestamp = System.currentTimeMillis().toString()
        val hash = generateHash(timestamp)

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

    private fun generateHash(timestamp: String): String {
        val md5 = MessageDigest.getInstance("MD5")
        val input = "$timestamp${BuildConfig.PRIVATE_KEY}${BuildConfig.PUBLIC_KEY}"
        val digest = md5.digest(input.toByteArray())

        return digest.joinToString("") { "%02x".format(it) }
    }
}