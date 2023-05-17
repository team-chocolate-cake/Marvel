package com.chocolatecake.marvel.data.remote.model

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("extension")
    val extension: String? = null,
    @SerializedName("path")
    val path: String? = null,
) {
    fun toUrl(): String {
        return if (isAvailablePath()) {
            listOf(
                "https://fandomwire.com/wp-content/uploads/2018/07/Marvel-Logo-14.jpg",
                "https://wallpapercave.com/wp/wp4034563.jpg"
            ).random()
        } else {
            "$path.$extension"
        }
    }

    private fun isAvailablePath(): Boolean {
        return path == null || path.split("/").last() == "image_not_available"
    }
}