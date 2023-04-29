package com.chocolatecake.marvel.data.model

import com.google.gson.annotations.SerializedName

data class UrlResponse(
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("url")
    val url: String? = null,
)