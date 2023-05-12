package com.chocolatecake.marvel.data.remote

import java.security.MessageDigest

object HashGenerator {

    val timestamp = System.currentTimeMillis().toString()

    fun generateHash(privateKey: String, publicKey: String): String {
        val md5 = MessageDigest.getInstance("MD5")
        val input = "$timestamp$privateKey$publicKey"
        val digest = md5.digest(input.toByteArray())

        return digest.joinToString("") { "%02x".format(it) }
    }
}