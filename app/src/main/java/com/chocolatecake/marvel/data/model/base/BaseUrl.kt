package com.chocolatecake.marvel.data.model.base

import com.google.gson.annotations.SerializedName

data class BaseUrl(
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("url")
    val url: String? = null,
)