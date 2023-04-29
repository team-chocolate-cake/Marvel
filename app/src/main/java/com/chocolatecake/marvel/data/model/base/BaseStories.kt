package com.chocolatecake.marvel.data.model.base

import com.google.gson.annotations.SerializedName

data class BaseStories(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("resourceURI")
    val resourceURI: String? = null,
    @SerializedName("type")
    val type: String? = null,
)
