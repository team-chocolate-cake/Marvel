package com.chocolatecake.marvel.data.util

import com.chocolatecake.marvel.BuildConfig
import java.security.MessageDigest

class GenerateHash() {
    fun generate(timestamp: String): String {
        val md5 = MessageDigest.getInstance("MD5")
        val input = "$timestamp${BuildConfig.PRIVATE_KEY}${BuildConfig.PUBLIC_KEY}"
        val digest = md5.digest(input.toByteArray())

        return digest.joinToString("") { "%02x".format(it) }
    }
}