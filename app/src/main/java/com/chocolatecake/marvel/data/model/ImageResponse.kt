package com.chocolatecake.marvel.data.model

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("extension")
    val extension: String? = null,
    @SerializedName("path")
    val path: String? = null,
)
