package com.chocolatecake.marvel.data.model.base

import com.google.gson.annotations.SerializedName

data class BaseCreators(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("resourceURI")
    val resourceURI: String? = null,
    @SerializedName("role")
    val role: String? = null,
)