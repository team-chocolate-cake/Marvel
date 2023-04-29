package com.chocolatecake.marvel.data.model.base

import com.google.gson.annotations.SerializedName

data class BaseImage(
    @SerializedName("extension")
    val extension: String? = null,
    @SerializedName("path")
    val path: String? = null,
)
