package com.chocolatecake.marvel.data.model.base

import com.google.gson.annotations.SerializedName

data class BaseItem(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("resourceURI")
    val resourceURI: String? = null,
)
